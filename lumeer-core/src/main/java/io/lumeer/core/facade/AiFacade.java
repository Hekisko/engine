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
package io.lumeer.core.facade;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lumeer.api.model.*;
import io.lumeer.api.model.Collection;
import io.lumeer.core.adapter.AiAdapter;
import io.lumeer.core.model.AiColumn;
import io.lumeer.core.model.AiTable;
import io.lumeer.core.model.AiTableResponse;
import io.lumeer.core.model.dto.AiColumnDto;
import io.lumeer.core.model.dto.AiResponseTablesDto;
import io.lumeer.core.model.dto.AiTableDto;
import io.lumeer.core.model.types.AbstractType;
import io.lumeer.core.util.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class AiFacade extends AbstractFacade {

   private AiAdapter aiAdapter;

   @PostConstruct
   public void init() {
      aiAdapter = new AiAdapter();
   }

   public AiTableResponse createTablesWithAi(String projectDescription) {
      ObjectMapper objectMapper = Utils.createJacksonObjectMapper();
      try {

         String aiResponseStr = aiAdapter.createTableWithAi(projectDescription);
         AiResponseTablesDto responseDto = getResponseTable(objectMapper.readTree(aiResponseStr), objectMapper);

         if (responseDto.isError()) {
            return returnErrorMessage(responseDto.getErrorMessage());
         }

         List<AiTable> AiTables = mapTableFromDto(responseDto, objectMapper);
         List<Collection> tables = mapAiTableToCollection(AiTables);

         return new AiTableResponse(
                 tables,
                 false,
                 null
         );
      } catch (JsonProcessingException e) {
         return returnErrorMessage(e.getMessage());
      }
   }

   private List<Collection> mapAiTableToCollection(List<AiTable> aiTables) {
      Permissions permissions = new Permissions();
      permissions.updateUserPermissions(Permission.buildWithRoles(getCurrentUserId(), Project.ROLES));

      return aiTables.stream().map(aiTable -> new Collection(
              UUID.randomUUID().toString(),
              aiTable.getName(),
              "fa-image",
              aiTable.getColor(),
              "",
              null,
              permissions,
              getAttributes(aiTable.getColumns()),
              null,
              null,
              null
      )).collect(Collectors.toList());
   }

   private Set<Attribute> getAttributes(List<AiColumn> columns) {
      return columns.stream().map(column -> new Attribute(
              UUID.randomUUID().toString(),
              column.getName(),
              "",
              getConstraint(column.getType()),
              null,
              null,
              null,
              0,
              null
      )).collect(Collectors.toSet());

   }

   //TODO upravit aby mapa bola podla FE
   private Constraint getConstraint(AbstractType type) {
      return new Constraint(
              type.getType().getLumeerType(),
              type.getConstraints()
      );
   }

   // Old testing code
   /*public List<Collection> createTablesWithAiTest(String projectDescription) {

      String test = "{\"error\":false,\"errorMessage\":null,\"tables\":[{\"color\":\"#0f483b\",\"columns\":[{\"name\":\"ID\",\"type\":{\"exampleValue\":{\"number\":1},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":false,\"compactMode\":false,\"currency\":null,\"forceSign\":false,\"max\":null,\"min\":0,\"roundedToNumberOfDigit\":0,\"thousandSeparated\":false}}},{\"name\":\"Property Name\",\"type\":{\"exampleValue\":{\"text\":\"Luxury Villa\"},\"type\":\"TEXT\",\"typeDetail\":{\"maximumLength\":100,\"minimumLength\":0}}},{\"name\":\"Location\",\"type\":{\"exampleValue\":{\"city\":\"Praha\",\"country\":null,\"county\":null,\"houseNumber\":\"452\",\"postalCode\":null,\"state\":\"Česká republika\",\"street\":\"Lumírova\"},\"type\":\"ADDRESS\",\"typeDetail\":{\"city\":true,\"country\":false,\"county\":false,\"houseNumber\":true,\"postalCode\":false,\"state\":true,\"street\":true}}},{\"name\":\"Price\",\"type\":{\"exampleValue\":{\"number\":2000000},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":false,\"compactMode\":false,\"currency\":\"EUR\",\"forceSign\":false,\"max\":null,\"min\":0,\"roundedToNumberOfDigit\":0,\"thousandSeparated\":true}}},{\"name\":\"Seller\",\"type\":{\"exampleValue\":{\"user\":\"janedoe@gmail.com\"},\"type\":\"USER\",\"typeDetail\":null}}],\"name\":\"Properties\"},{\"color\":\"#0f513b\",\"columns\":[{\"name\":\"ID\",\"type\":{\"exampleValue\":{\"number\":1},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":false,\"compactMode\":false,\"currency\":null,\"forceSign\":false,\"max\":null,\"min\":0,\"roundedToNumberOfDigit\":0,\"thousandSeparated\":false}}},{\"name\":\"Property ID\",\"type\":{\"exampleValue\":{\"number\":1},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":false,\"compactMode\":false,\"currency\":null,\"forceSign\":false,\"max\":null,\"min\":0,\"roundedToNumberOfDigit\":0,\"thousandSeparated\":false}}},{\"name\":\"Viewer\",\"type\":{\"exampleValue\":{\"user\":\"janedoe2@gmail.com\"},\"type\":\"USER\",\"typeDetail\":null}},{\"name\":\"Date\",\"type\":{\"exampleValue\":{\"date\":1706711455},\"type\":\"DATE\",\"typeDetail\":{\"format\":\"DD.MM.YYYY H:mm\",\"isUTC\":true,\"maximum\":null,\"minimum\":null}}}],\"name\":\"Viewings\"}]}";
      String test2 = "{\"error\":false,\"errorMessage\":null,\"tables\":[{\"color\":\"#0f483b\",\"columns\":[{\"name\":\"Address\",\"type\":{\"exampleValue\":{\"city\":\"Example City\",\"country\":\"Example Country\",\"county\":\"Example County\",\"houseNumber\":\"123\",\"postalCode\":\"12345\",\"state\":\"Example State\",\"street\":\"Example Street\"},\"type\":\"ADDRESS\",\"typeDetail\":{\"city\":true,\"country\":true,\"county\":true,\"houseNumber\":true,\"postalCode\":true,\"state\":true,\"street\":true}}},{\"name\":\"Checkbox\",\"type\":{\"exampleValue\":{\"selected\":true},\"type\":\"CHECKBOX\",\"typeDetail\":null}},{\"name\":\"Color\",\"type\":{\"exampleValue\":{\"color\":\"#FFFFFF\"},\"type\":\"COLOR\",\"typeDetail\":null}},{\"name\":\"Coordinates\",\"type\":{\"exampleValue\":{\"first\":\"49.233131\",\"second\":\"16.570183\"},\"type\":\"COORDINATES\",\"typeDetail\":{\"format\":\"DECIMAL\",\"precision\":6}}},{\"name\":\"Date\",\"type\":{\"exampleValue\":{\"date\":1632900000},\"type\":\"DATE\",\"typeDetail\":{\"format\":\"DD.MM.YYYY\",\"isUTC\":true,\"maximum\":0,\"minimum\":0}}},{\"name\":\"Duration\",\"type\":{\"exampleValue\":{\"duration\":3600},\"type\":\"DURATION\",\"typeDetail\":null}}],\"name\":\"Marketing Department Table\"},{\"color\":\"#0f513b\",\"columns\":[{\"name\":\"File Attachment\",\"type\":{\"exampleValue\":null,\"type\":\"FILE_ATTACHMENT\",\"typeDetail\":null}},{\"name\":\"Link\",\"type\":{\"exampleValue\":{\"link\":\"https://example.com\",\"title\":\"Example Link\"},\"type\":\"LINK\",\"typeDetail\":null}},{\"name\":\"Number\",\"type\":{\"exampleValue\":{\"number\":123.45},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":true,\"compactMode\":false,\"currency\":\"USD\",\"forceSign\":false,\"max\":1000.0,\"min\":-1000.0,\"roundedToNumberOfDigit\":2,\"thousandSeparated\":true}}},{\"name\":\"Percentage\",\"type\":{\"exampleValue\":{\"percentage\":50},\"type\":\"PERCENTAGE\",\"typeDetail\":{\"color\":\"#FF0000\",\"display\":\"TEXT\",\"maximum\":100,\"minimum\":0,\"roundedToNumberOfDigit\":2}}},{\"name\":\"Selection\",\"type\":{\"exampleValue\":{\"selected\":[\"Option 1\"]},\"type\":\"SELECTION\",\"typeDetail\":{\"allowMultipleValues\":true,\"name\":\"Options\",\"values\":[{\"color\":\"#FF0000\",\"name\":\"Option 1\"},{\"color\":\"#00FF00\",\"name\":\"Option 2\"}]}}},{\"name\":\"Text\",\"type\":{\"exampleValue\":{\"text\":\"Example Text\"},\"type\":\"TEXT\",\"typeDetail\":{\"maximumLength\":100,\"minimumLength\":0}}},{\"name\":\"User\",\"type\":{\"exampleValue\":{\"user\":\"example@example.com\"},\"type\":\"USER\",\"typeDetail\":null}}],\"name\":\"Marketing Department Table 2\"}]}";
      String test3 = "{\"error\":false,\"errorMessage\":null,\"tables\":[{\"color\":\"#0f483b\",\"columns\":[{\"name\":\"Place Name\",\"type\":{\"exampleValue\":{\"text\":\"Place Name\"},\"type\":\"TEXT\",\"typeDetail\":{\"maximumLength\":100,\"minimumLength\":0}}},{\"name\":\"Category\",\"type\":{\"exampleValue\":{\"selected\":[\"Attractions\"]},\"type\":\"SELECTION\",\"typeDetail\":{\"allowMultipleValues\":true,\"name\":\"Category\",\"values\":[{\"color\":\"#f4cccc\",\"name\":\"Attractions\"},{\"color\":\"#fce5cd\",\"name\":\"Restaurants\"},{\"color\":\"#fff2cc\",\"name\":\"Hotels\"}]}}},{\"name\":\"Coordinates\",\"type\":{\"exampleValue\":{\"coordinates\":{\"first\":\"49° 13' 59\\\" N\",\"second\":\"16° 34' 13\\\" E\"}},\"type\":\"COORDINATES\",\"typeDetail\":{\"format\":\"DEGREES\",\"precision\":2}}},{\"name\":\"Personal Rating\",\"type\":{\"exampleValue\":{\"number\":4.5},\"type\":\"NUMBER\",\"typeDetail\":{\"canBeNegative\":false,\"compactMode\":false,\"currency\":null,\"forceSign\":false,\"max\":5,\"min\":0,\"roundedToNumberOfDigit\":1,\"thousandSeparated\":false}}},{\"name\":\"Review\",\"type\":{\"exampleValue\":{\"text\":\"Great place to visit!\"},\"type\":\"TEXT\",\"typeDetail\":{\"maximumLength\":500,\"minimumLength\":0}}}],\"name\":\"Travel Destinations\"}]}";
      String resultString = "{\"created\":1708869430,\"choices\":[{\"finish_reason\":\"stop\",\"index\":0,\"logprobs\":null,\"message\":{\"content\":\"{\\\"error\\\":false,\\\"errorMessage\\\":null,\\\"tables\\\":[{\\\"color\\\":\\\"#0f483b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"number\\\":1},\\\"type\\\":\\\"NUMBER\\\",\\\"typeDetail\\\":{\\\"canBeNegative\\\":false,\\\"compactMode\\\":false,\\\"currency\\\":null,\\\"forceSign\\\":false,\\\"max\\\":null,\\\"min\\\":0,\\\"roundedToNumberOfDigit\\\":0,\\\"thousandSeparated\\\":false}}},{\\\"name\\\":\\\"Property Name\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"text\\\":\\\"Luxury Villa\\\"},\\\"type\\\":\\\"TEXT\\\",\\\"typeDetail\\\":{\\\"maximumLength\\\":100,\\\"minimumLength\\\":0}}},{\\\"name\\\":\\\"Location\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"city\\\":\\\"Praha\\\",\\\"country\\\":null,\\\"county\\\":null,\\\"houseNumber\\\":\\\"452\\\",\\\"postalCode\\\":null,\\\"state\\\":\\\"Česká republika\\\",\\\"street\\\":\\\"Lumírova\\\"},\\\"type\\\":\\\"ADDRESS\\\",\\\"typeDetail\\\":{\\\"city\\\":true,\\\"country\\\":false,\\\"county\\\":false,\\\"houseNumber\\\":true,\\\"postalCode\\\":false,\\\"state\\\":true,\\\"street\\\":true}}},{\\\"name\\\":\\\"Price\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"number\\\":2000000},\\\"type\\\":\\\"NUMBER\\\",\\\"typeDetail\\\":{\\\"canBeNegative\\\":false,\\\"compactMode\\\":false,\\\"currency\\\":\\\"EUR\\\",\\\"forceSign\\\":false,\\\"max\\\":null,\\\"min\\\":0,\\\"roundedToNumberOfDigit\\\":0,\\\"thousandSeparated\\\":true}}},{\\\"name\\\":\\\"Seller\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"user\\\":\\\"janedoe@gmail.com\\\"},\\\"type\\\":\\\"USER\\\",\\\"typeDetail\\\":null}}],\\\"name\\\":\\\"Properties\\\"},{\\\"color\\\":\\\"#0f513b\\\",\\\"columns\\\":[{\\\"name\\\":\\\"ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"number\\\":1},\\\"type\\\":\\\"NUMBER\\\",\\\"typeDetail\\\":{\\\"canBeNegative\\\":false,\\\"compactMode\\\":false,\\\"currency\\\":null,\\\"forceSign\\\":false,\\\"max\\\":null,\\\"min\\\":0,\\\"roundedToNumberOfDigit\\\":0,\\\"thousandSeparated\\\":false}}},{\\\"name\\\":\\\"Property ID\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"number\\\":1},\\\"type\\\":\\\"NUMBER\\\",\\\"typeDetail\\\":{\\\"canBeNegative\\\":false,\\\"compactMode\\\":false,\\\"currency\\\":null,\\\"forceSign\\\":false,\\\"max\\\":null,\\\"min\\\":0,\\\"roundedToNumberOfDigit\\\":0,\\\"thousandSeparated\\\":false}}},{\\\"name\\\":\\\"Viewer\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"user\\\":\\\"janedoe2@gmail.com\\\"},\\\"type\\\":\\\"USER\\\",\\\"typeDetail\\\":null}},{\\\"name\\\":\\\"Date\\\",\\\"type\\\":{\\\"exampleValue\\\":{\\\"date\\\":1706711455},\\\"type\\\":\\\"DATE\\\",\\\"typeDetail\\\":{\\\"format\\\":\\\"DD.MM.YYYY H:mm\\\",\\\"isUTC\\\":true,\\\"maximum\\\":null,\\\"minimum\\\":null}}}],\\\"name\\\":\\\"Viewings\\\"}]}\",\"role\":\"assistant\"}}],\"id\":\"chatcmpl-8w9I6mj6ksJroru7QcsHZklyCQME0\",\"model\":\"ft:gpt-3.5-turbo-1106:personal::8tgu4JMB\",\"object\":\"chat.completion\",\"system_fingerprint\":\"fp_811d5fcad5\",\"usage\":{\"completion_tokens\":173,\"prompt_tokens\":35,\"total_tokens\":208}}";

      ObjectMapper objectMapper = Utils.createJacksonObjectMapper();
      try {
         JsonNode root = objectMapper.readTree(resultString);
         System.out.println(root.get("choices").get(0).get("message").get("content").asText());

         AiResponseTablesDto responseTablesDto = getResponseTable(root, objectMapper);
         //List<AiTable> tables = mapTableFromDto(responseTablesDto, objectMapper);
         List<AiTable> tables = mapTableFromDto(objectMapper.readValue(test, AiResponseTablesDto.class), objectMapper);
         System.out.println(tables);
      } catch (NullPointerException | JsonProcessingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }

       //return new Collection();
      return new ArrayList<>();
   }

    */

   private List<AiTable> mapTableFromDto(AiResponseTablesDto responseTablesDto, ObjectMapper objectMapper) throws IllegalStateException{

      List<AiTable> result = new ArrayList<>();

      for (AiTableDto tableDto: responseTablesDto.getTables()) {

         result.add(new AiTable(
                 tableDto.getColor(),
                 tableDto.getName(),
                 tableDto.getColumns().stream().map(aiColumnDto -> {
                     try {
                        System.out.println(aiColumnDto);
                         return mapColumnFromDto(aiColumnDto, objectMapper);
                     } catch (JsonProcessingException e) {
                         throw new IllegalStateException(e);
                     }
                 }).toList()
         ));
      }
      return result;
   }

   private AiColumn mapColumnFromDto(AiColumnDto aiColumnDto, ObjectMapper objectMapper) throws JsonProcessingException {

      return new AiColumn(
              aiColumnDto.getName(),
              aiColumnDto.getType().getType().getType(aiColumnDto.getType(), objectMapper)
      );
   }


   private AiResponseTablesDto getResponseTable(JsonNode root, ObjectMapper objectMapper) throws JsonProcessingException {
      return objectMapper.readValue(root.get("choices").get(0).get("message").get("content").asText(), AiResponseTablesDto.class);
   }

   private AiTableResponse returnErrorMessage(String error) {
      return new AiTableResponse(
              null,
              true,
              error
      );
   }


}
