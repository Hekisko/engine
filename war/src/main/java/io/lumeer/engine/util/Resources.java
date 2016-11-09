/*
 * -----------------------------------------------------------------------\
 * Lumeer
 *  
 * Copyright (C) 2016 the original author or authors.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -----------------------------------------------------------------------/
 */
package io.lumeer.engine.util;

import io.lumeer.engine.annotation.SystemDataStorage;
import io.lumeer.engine.api.data.DataStorage;
import io.lumeer.engine.api.data.StorageConnection;
import io.lumeer.mongodb.MongoDbStorage;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 *
 * @author <a href="mailto:marvenec@gmail.com">Martin Večeřa</a>
 */
public class Resources {

   private final String SYSTEM_DB_HOST = System.getProperty("lumeer.sysdb.host", "localhost");
   private static final int SYSTEM_DB_PORT = Integer.getInteger("lumeer.sysdb.port", 27017);
   private static final String SYSTEM_DB_NAME = System.getProperty("lumeer.sysdb.name", "lumeer");
   private static final String SYSTEM_DB_USER = System.getProperty("lumeer.sysdb.user", "");
   private static final String SYSTEM_DB_PASSWORD = System.getProperty("lumeer.sysdb.password", "");

   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }

   /**
    * Produces system storage for user data etc.
    *
    * @return System data storage.
    */
   @Produces
   @SystemDataStorage
   @RequestScoped
   public DataStorage getSystemDataStorage() {
      final MongoDbStorage storage = new MongoDbStorage();
      storage.connect(new StorageConnection(SYSTEM_DB_HOST, SYSTEM_DB_PORT, SYSTEM_DB_USER, SYSTEM_DB_PASSWORD), SYSTEM_DB_NAME);

      return storage;
   }

}
