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

private const val JSON_REQUEST_TEMPLATE = "{\"messages\":[{\"content\":\"Your job is to help users of Lumeer.io\",\"role\":\"system\"},{\"content\":\"%s\",\"role\":\"user\"}],\"model\":\"ft:gpt-3.5-turbo-1106:personal::8tgu4JMB\"}"
private const val CREATE_TABLE_JSON_TEMPLATE = "According to the following description, create tables for the Lumeer.io application. If an error occurs during creation, write it into your json response as true on error, the error message into errorMessage and set tables to null. The error message must be written in such a way that it can be directly displayed to the user. For the answer, use the standard json response {“error”: boolean, “errorMessage”: string, “tables”: <Your Tables json>}. Use only such data types for individual columns that you know for the Lumeer.io application. The description is as follows: \\\"%s\\\". For the resulting json, use the English, also keep the property names in English - as you have learned them."

class AiAdapter() {
   fun createTableWithAi(projectDescription: String): String {
      return getAiResponse(createTableRequestStr(projectDescription))
   }

   private fun createTableRequestStr(projectDescription: String): String {
      return JSON_REQUEST_TEMPLATE.format(
              CREATE_TABLE_JSON_TEMPLATE.format(
                      projectDescription
              )
      )
   }

   private fun getAiResponse(requestStr: String): String {

      /*val client = ClientBuilder.newBuilder().build()
      val response = client.target("https://api.openai.com/v1/chat/completions")
              .request(MediaType.APPLICATION_JSON)
              .header("Content-Type", MediaType.APPLICATION_JSON)
              .header("Authorization", "Bearer PLACE_KEY_HERE")
              .buildPost(Entity.json(requestStr))
              .invoke()

      println(response.readEntity(String::class.java))

      return response.toString()
       */

      //ONLY FOR TESTING
      val resultString = "{\"created\":1708869430,\"choices\":[{\"finish_reason\":\"stop\",\"index\":0,\"logprobs\":null,\"message\":{\"content\":\"{\\\"error\\\":false,\\\"errorMessage\\\":null,\\\"tables\\\":[{\\\"color\\\":\\\"#0f483b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"Risk ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"R1\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Risk Title\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"Risk Title\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Risk Description\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"Risk Description\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":500,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Probability\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"percentage\\\":80},\\\"type\\\":\\\"PERCENTAGE\\\",\\\"typeDetail\\\":{\\\"color\\\":\\\"#0a183b\\\",\\\"display\\\":\\\"TEXT\\\",\\\"maximum\\\":100,\\\"minimum\\\":0,\\\"roundedToNumberOfDigit\\\":0}}},{\\\"name\\\":\\\"Consequences\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"Consequences\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":200,\\\"minimumLength\\\":0}}}],\\\"name\\\":\\\"Risk Identification Table\\\"},{\\\"color\\\":\\\"#0f513b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"Risk ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"R1\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Solution Strategy\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"selected\\\":[\\\"Elimination\\\"]},\\\"type\\\":\\\"SELECTION\\\",\\\"typeDetail\\\":{\\\"allowMultipleValues\\\":false,\\\"name\\\":\\\"Risk strategies\\\",\\\"values\\\":[{\\\"color\\\":\\\"#f4cccc\\\",\\\"name\\\":\\\"Elimination\\\"},{\\\"color\\\":\\\"#fce5cd\\\",\\\"name\\\":\\\"Acceptance\\\"},{\\\"color\\\":\\\"#fff2cc\\\",\\\"name\\\":\\\"Transfer\\\"}]}}},{\\\"name\\\":\\\"Responsible Person\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"user\\\":\\\"janedoe@gmail.com\\\"},\\\"type\\\":\\\"USER\\\",\\\"typeDetail\\\":null}},{\\\"name\\\":\\\"From\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"date\\\":1703111455},\\\"type\\\":\\\"DATE\\\",\\\"typeDetail\\\":{\\\"format\\\":\\\"DD.MM.YYYY\\\",\\\"isUTC\\\":true,\\\"maximum\\\":null,\\\"minimum\\\":null}}},{\\\"name\\\":\\\"To\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"date\\\":1706711455},\\\"type\\\":\\\"DATE\\\",\\\"typeDetail\\\":{\\\"format\\\":\\\"DD.MM.YYYY\\\",\\\"isUTC\\\":true,\\\"maximum\\\":null,\\\"minimum\\\":null}}}],\\\"name\\\":\\\"Risk Management Plan Table\\\"},{\\\"color\\\":\\\"#0f483b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"Risk ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"R1\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Probability\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"percentage\\\":80},\\\"type\\\":\\\"PERCENTAGE\\\",\\\"typeDetail\\\":{\\\"color\\\":\\\"#0a183b\\\",\\\"display\\\":\\\"TEXT\\\",\\\"maximum\\\":100,\\\"minimum\\\":0,\\\"roundedToNumberOfDigit\\\":0}}},{\\\"name\\\":\\\"Impact\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"selected\\\":[\\\"Low\\\"]},\\\"type\\\":\\\"SELECTION\\\",\\\"typeDetail\\\":{\\\"allowMultipleValues\\\":false,\\\"name\\\":\\\"Impact\\\",\\\"values\\\":[{\\\"color\\\":\\\"#f4cccc\\\",\\\"name\\\":\\\"High\\\"},{\\\"color\\\":\\\"#fce5cd\\\",\\\"name\\\":\\\"Medium\\\"},{\\\"color\\\":\\\"#fff2cc\\\",\\\"name\\\":\\\"Low\\\"}]}}},{\\\"name\\\":\\\"Total Risk Score\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"number\\\":1},\\\"type\\\":\\\"NUMBER\\\",\\\"typeDetail\\\":{\\\"canBeNegative\\\":false,\\\"compactMode\\\":false,\\\"currency\\\":null,\\\"forceSign\\\":false,\\\"max\\\":null,\\\"min\\\":0,\\\"roundedToNumberOfDigit\\\":0,\\\"thousandSeparated\\\":false}}}],\\\"name\\\":\\\"Risk Assessment Table\\\"},{\\\"color\\\":\\\"#0f513b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"Risk ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"R1\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Status\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"selected\\\":[\\\"Waiting\\\"]},\\\"type\\\":\\\"SELECTION\\\",\\\"typeDetail\\\":{\\\"allowMultipleValues\\\":false,\\\"name\\\":\\\"Status\\\",\\\"values\\\":[{\\\"color\\\":\\\"#f4cccc\\\",\\\"name\\\":\\\"Waiting\\\"},{\\\"color\\\":\\\"#fce5cd\\\",\\\"name\\\":\\\"In progress\\\"},{\\\"color\\\":\\\"#fff2cc\\\",\\\"name\\\":\\\"Resolved\\\"}]}}},{\\\"name\\\":\\\"Last Update Date\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"date\\\":1706711455},\\\"type\\\":\\\"DATE\\\",\\\"typeDetail\\\":{\\\"format\\\":\\\"DD.MM.YYYY\\\",\\\"isUTC\\\":true,\\\"maximum\\\":null,\\\"minimum\\\":null}}},{\\\"name\\\":\\\"Notes\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"Notes\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}}],\\\"name\\\":\\\"Risk Tracking Table\\\"}]}\",\"role\":\"assistant\"}}],\"id\":\"chatcmpl-8w9I6mj6ksJroru7QcsHZklyCQME0\",\"model\":\"ft:gpt-3.5-turbo-1106:personal::8tgu4JMB\",\"object\":\"chat.completion\",\"system_fingerprint\":\"fp_811d5fcad5\",\"usage\":{\"completion_tokens\":173,\"prompt_tokens\":35,\"total_tokens\":208}}"
      return resultString
   }

}
