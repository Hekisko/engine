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

import io.lumeer.api.model.Collection
import io.lumeer.storage.api.dao.*

class CollectionAdapter(val collectionDao: CollectionDao, val favoriteItemDao: FavoriteItemDao, val documentDao: DocumentDao) {

   fun getFavoriteCollectionIds(userId: String, projectId: String): Set<String> = favoriteItemDao.getFavoriteCollectionIds(userId, projectId)

   fun isFavorite(collectionId: String, userId: String, projectId: String): Boolean = getFavoriteCollectionIds(userId, projectId).contains(collectionId)

   fun getDocumentsCountByCollection(collectionId: String) = documentDao.getDocumentsCountByCollection(collectionId)

   fun getDocumentsCounts() = documentDao.documentsCounts

   fun getDocumentsCount() = getDocumentsCounts().values.sum()

   fun mapCollectionData(collection: Collection, userId: String, projectId: String) = collection.apply {
      isFavorite = isFavorite(collection.id, userId, projectId)
       documentsCount = getDocumentsCountByCollection(collection.id)
   }

   fun mapCollectionsData(collections: List<Collection>, userId: String, projectId: String): List<Collection> {
      val favoriteCollectionIds = getFavoriteCollectionIds(userId, projectId)
      val documentsCounts = getDocumentsCounts()

      return collections.onEach {
         it.isFavorite = favoriteCollectionIds.contains(it.id)
         it.documentsCount = documentsCounts[it.id]?.or(0)
      }
   }

}
