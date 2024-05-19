/*
 * Lumeer: Modern Data Definition and Processing Platform
 *
 * Copyright (C) since 2017 Lumeer.io, s.r.o. and/or its affiliates.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.lumeer.core.adapter

import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType

private const val TRAINED_MODEL = "ft:gpt-3.5-turbo-0125:personal::9QMLhRZb"
private const val MODEL_3_5 = "gpt-3.5-turbo-0125"
private const val JSON_REQUEST_TEMPLATE = "{\"messages\":[{\"content\":\"Your job is to help users of Lumeer.io\",\"role\":\"system\"},{\"content\":\"%s\",\"role\":\"user\"}],\"model\":\"%s\", \"temperature\":0.2, \"max_tokens\":2048}"
private const val CREATE_TABLE_JSON_TEMPLATE = "According to the following description, create tables for the Lumeer.io application. If an error occurs during creation, write it into your json response as true on error, the error message into errorMessage and set tables to null. The error message must be written in such a way that it can be directly displayed to the user. For the answer, use the standard json response {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"tables\\\": [<Your Tables json>]} where one table in <Your Tables json> looks like this {\\\"color\\\": <Color of table>, \\\"name\\\": <Name of table>, \\\"colums\\\": [{\\\"name\\\": <Name of column>, \\\"type:\\\": <Type in json>}, ...]}. <Type in json> types can be used only those you know for Lumeer.io in this format {\\\"type\\\": <Data type from Lumeer>, \\\"typeDetail\\\": <Type details>, \\\"exampleValue\\\": <Example value>}. The description is as follows: \\\"%s\\\". For the resulting json, use the English, also keep the property names in English - as you have learned them. Response must be valid json - check it before you send me back response."
private const val CREATE_TABLE_FROM_EXISTING_JSON_TEMPLATE = "According to the following description, update tables created before for the Lumeer.io application. Here are tables created before: \\\"%S\\\" If an error occurs during creation, write it into your json response as true on error, the error message into errorMessage and set tables to null. The error message must be written in such a way that it can be directly displayed to the user. For the answer, use the standard json response {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"tables\\\": <Your Tables json>} where one table in <Your Tables json> looks like this {\\\"color\\\": <Color of table>, \\\"name\\\": <Name of table>, \\\"colums\\\": [{\\\"name\\\": <Name of column>, \\\"type:\\\": <Type in json>}, ...]]}. <Type in json> types can be used only those you know for Lumeer.io in this format {\\\"type\\\": <Data type from Lumeer>, \\\"typeDetail\\\": <Type details>, \\\"exampleValue\\\": <Example value>}. Use only such data types for individual columns that you know for the Lumeer.io application. The description is as follows: \\\"%s\\\". For the resulting json, use the English, also keep the property names in English - as you have learned them. Response must be valid json - check it before you send me back response."
private const val ASSISTED_WRITING_EXPAND_JSON_TEMPLATE = "The following text should be written more extensively, preserving its idea and writing style. Here is the text \\\"%s\\\". Your response must be in json format like this {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"text\\\": \\\"<HERE GOES NEW TEXT>\\\"}. If any error occurs, set <error> to true and set <errorMessage> to error message and resulting <text> will be null. Response must be valid json - check it before you send me back response."
private const val ASSISTED_WRITING_CONTRACT_JSON_TEMPLATE = "The following text should be written more minimally, preserving its idea and writing style. Here is the text \\\"%s\\\". Your response must be in json format like this {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"text\\\": \\\"<HERE GOES NEW TEXT>\\\"}. If any error occurs, set <error> to true and set <errorMessage> to error message and resulting <text> will be null. Response must be valid json - check it before you send me back response."
private const val CHECK_DATA_JSON_TEMPLATE = "Check the values from Lumeer.io in the array and return the values that do not match among the others in your response. Input array is %s. Your response must be in json format like this {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"values\\\": [<HERE GOES VALUES, THAT DO NOT MATCH AMONG THE OTHERS>]}. If any error occurs, set <error> to true and set <errorMessage> to error message and resulting <values> will be null. Response must be valid json - check it before you send me back response."
private const val TEMPLATE_JSON_TEMPLATE = "I am providing you with possible template names for the project in the Lumeer application: [\\\"Issue Tracker\\\", \\\"Scrum\\\", \\\"Product Roadmap\\\", \\\"Product Launch\\\", \\\"Test Case Management System\\\", \\\"Inventory Tracking\\\", \\\"Budget Planning\\\", \\\"Fleet Management\\\", \\\"Marketing Campaign\\\", \\\"Smart Customer Relationship Management\\\", \\\"Jobs Tracking\\\", \\\"Project Portfolio Management\\\", \\\"Workflow Automation\\\", \\\"Clients Orders Management\\\", \\\"Advanced Task Management\\\", \\\"Company Controlling\\\", \\\"Civil Engineering Projects\\\", \\\"Editorial Calendar\\\", \\\"Empty Project\\\", \\\"Wedding Planning\\\", \\\"Yoga Tracker\\\", \\\"Candidates Coordination\\\", \\\"OKR Tracking\\\", \\\"Time Management\\\", \\\"Remote Work\\\", \\\"Human Resources\\\", \\\"Simple Project Portfolio Tracker\\\", \\\"Task Tracker\\\", \\\"Supply Chain Management\\\", \\\"Sales CMR\\\", \\\"Cemetery Management\\\"]. Your task, based on this description: \\\"%s\\\", is to select the 3 most suitable templates and return them as an array of String values. Your response must be in json format like this {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"result\\\": [\\\"<HERE GOES TEMPLATE NAME 1>\\\", \\\"<HERE GOES TEMPLATE NAME 2>\\\", \\\"<HERE GOES TEMPLATE NAME 3>\\\"]}. If any error occurs, set <error> to true and set <errorMessage> to error message and resulting <result> will be null. Response must be valid json - check it before you send me back response."
private const val SUGGEST_DATA_TYPE_TEMPLATE = "Based on the values from this field %s, determine the data types of these values that you know from Lumeer.io. Your response must be in json format like this  {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"type\\\": <HERE GOES DATA TYPE JSON, WHICH YOU DETERMINED>}. If any error occurs, set <error> to true, set <errorMessage> to error message and resulting <type> will be null. Response must be valid json - check it before you send me back response."
private const val MASS_DELETE_TEMPLATE = "The following array represents records from the table. The first element is the table header, where column names are defined. Here is the array [%s]. According to this requirement \\\"%s\\\", return an array with the IDs of records that should be deleted according to the requirement. Your response must be in json format like this {\\\"error\\\": boolean, \\\"errorMessage\\\": string, \\\"values\\\": [<HERE GOES IDS TO BE DELETED>]}. \\\"values\\\" should looks like this [\\\"66368f487b612b09d1f40b13\\\",\\\"66368f487b612b09d1f40b12\\\",\\\"66368f487b612b09d1f40b11\\\"]. If any error occurs, set <error> to true and set <errorMessage> to error message and resulting <values> will be null. Response must be valid json - check it before you send me back response."

class AiAdapter(private val apiKey: String) {

   fun createTableWithAi(projectDescription: String, oldAiTablesGeneratedStr: String): String {
      if (oldAiTablesGeneratedStr.isNotEmpty()) {
         return getAiResponse(createTableFromExistingRequestStr(projectDescription, oldAiTablesGeneratedStr))
      }
      return getAiResponse(createTableRequestStr(projectDescription))
   }

   fun assistedWritingExpand(input: String): String {
      return getAiResponse(createAssistedExpandWritingRequestStr(input))
   }

   fun assistedWritingContract(input: String): String {
      return getAiResponse(createAssistedContractWritingRequestStr(input))
   }

   fun template(projectDescription: String): String {
      return getAiResponse(createTemplateRequestStr(projectDescription))
   }

   fun suggestDataType(presentedData: String): String {
      return getAiResponse(createSuggestDataTypeRequestStr(presentedData))
   }

   fun checkData(dataToBeChecked: String): String {
      return getAiResponse(createCheckDataRequestStr(dataToBeChecked))
   }

   fun massDelete(deleteDescription: String, data: String): String {
      return getAiResponse(createMassDeleteRequestStr(deleteDescription, data))
   }

   private fun createTableRequestStr(projectDescription: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              CREATE_TABLE_JSON_TEMPLATE.format(
                      projectDescription
              ),
              TRAINED_MODEL
      )
   }

   private fun createTableFromExistingRequestStr(projectDescription: String, oldAiTablesGeneratedStr: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              CREATE_TABLE_FROM_EXISTING_JSON_TEMPLATE.format(
                      oldAiTablesGeneratedStr,
                      projectDescription
              ),
              TRAINED_MODEL
      )
   }

   private fun createAssistedExpandWritingRequestStr(input: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              ASSISTED_WRITING_EXPAND_JSON_TEMPLATE.format(
                      input
              ),
              MODEL_3_5
      )
   }

   private fun createAssistedContractWritingRequestStr(input: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              ASSISTED_WRITING_CONTRACT_JSON_TEMPLATE.format(
                      input
              ),
              MODEL_3_5
      )
   }

   private fun createMassDeleteRequestStr(deleteDescription: String, data: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              MASS_DELETE_TEMPLATE.format(
                      data,
                      deleteDescription
              ),
              MODEL_3_5
      )
   }

   private fun createTemplateRequestStr(projectDescription: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              TEMPLATE_JSON_TEMPLATE.format(
                      projectDescription
              ),
              MODEL_3_5
      )
   }

   private fun createSuggestDataTypeRequestStr(presentedData: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              SUGGEST_DATA_TYPE_TEMPLATE.format(
                      presentedData
              ),
              TRAINED_MODEL
      )
   }

   private fun createCheckDataRequestStr(dataToBeChecked: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              CHECK_DATA_JSON_TEMPLATE.format(
                      dataToBeChecked
              ),
              TRAINED_MODEL
      )
   }

   private fun getAiResponse(requestStr: String): String {

      val client = ClientBuilder.newBuilder().build()
      val response = client.target("https://api.openai.com/v1/chat/completions")
              .request(MediaType.APPLICATION_JSON)
              .header("Content-Type", MediaType.APPLICATION_JSON)
              .header("Authorization", "Bearer " + apiKey)
              .post(Entity.json(requestStr.replace("\n", "\\n")))

      return response.readEntity(String::class.java)
   }

}
