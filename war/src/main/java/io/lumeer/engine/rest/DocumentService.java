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
package io.lumeer.engine.rest;

import io.lumeer.engine.api.constraint.InvalidConstraintException;
import io.lumeer.engine.api.data.DataDocument;
import io.lumeer.engine.api.exception.CollectionMetadataDocumentNotFoundException;
import io.lumeer.engine.api.exception.CollectionNotFoundException;
import io.lumeer.engine.api.exception.DbException;
import io.lumeer.engine.api.exception.DocumentNotFoundException;
import io.lumeer.engine.api.exception.UnauthorizedAccessException;
import io.lumeer.engine.controller.CollectionMetadataFacade;
import io.lumeer.engine.controller.DocumentFacade;
import io.lumeer.engine.controller.DocumentMetadataFacade;
import io.lumeer.engine.controller.SecurityFacade;
import io.lumeer.engine.controller.UserFacade;
import io.lumeer.engine.controller.VersionFacade;
import io.lumeer.engine.rest.dao.AccessRightsDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@Path("/collections/{collectionName}/documents")
@RequestScoped
public class DocumentService implements Serializable {

   private static final long serialVersionUID = 5645433756019847986L;

   @Inject
   private CollectionMetadataFacade collectionMetadataFacade;

   @Inject
   private DocumentFacade documentFacade;

   @Inject
   private DocumentMetadataFacade documentMetadataFacade;

   @Inject
   private SecurityFacade securityFacade;

   @Inject
   private VersionFacade versionFacade;

   @Inject
   private UserFacade userFacade;

   @POST
   @Path("/")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public String createDocument(final @PathParam("collectionName") String collectionName, final DataDocument document) throws DbException, InvalidConstraintException {
      if (collectionName == null || document == null) {
         throw new IllegalArgumentException();
      }
      return documentFacade.createDocument(getInternalName(collectionName), document);
   }

   @DELETE
   @Path("/{documentId}")
   public void dropDocument(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws DbException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      documentFacade.dropDocument(getInternalName(collectionName), documentId);
   }

   @GET
   @Path("/{documentId}")
   @Produces(MediaType.APPLICATION_JSON)
   public DataDocument readDocument(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws DbException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      return documentFacade.readDocument(getInternalName(collectionName), documentId);
   }

   @PUT
   @Path("/")
   @Consumes(MediaType.APPLICATION_JSON)
   public void updateDocument(final @PathParam("collectionName") String collectionName, final DataDocument updatedDocument) throws DbException, InvalidConstraintException {
      if (collectionName == null || updatedDocument == null) {
         throw new IllegalArgumentException();
      }
      documentFacade.updateDocument(getInternalName(collectionName), updatedDocument);
   }

   @POST
   @Path("/{documentId}/meta/{attributeName}")
   @Consumes(MediaType.APPLICATION_JSON)
   public void addDocumentMetadata(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId, final @PathParam("attributeName") String attributeName, final Object value) throws CollectionNotFoundException, DocumentNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null || attributeName == null || value == null) {
         throw new IllegalArgumentException();
      }
      documentMetadataFacade.putDocumentMetadata(getInternalName(collectionName), documentId, attributeName, value);
   }

   @GET
   @Path("/{documentId}/meta")
   @Produces(MediaType.APPLICATION_JSON)
   public Map<String, Object> readDocumentMetadata(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws CollectionNotFoundException, DocumentNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      return documentMetadataFacade.readDocumentMetadata(getInternalName(collectionName), documentId);
   }

   @PUT
   @Path("/{documentId}/meta")
   @Consumes(MediaType.APPLICATION_JSON)
   public void updateDocumentMetadata(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId, final DataDocument metadata) throws CollectionNotFoundException, DocumentNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null || metadata == null) {
         throw new IllegalArgumentException();
      }
      documentMetadataFacade.updateDocumentMetadata(getInternalName(collectionName), documentId, metadata);
   }

   @GET
   @Path("/{documentId}/versions")
   @Produces(MediaType.APPLICATION_JSON)
   public List<DataDocument> searchHistoryChanges(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws CollectionNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      return versionFacade.getDocumentVersions(getInternalName(collectionName), documentId);
   }

   @POST
   @Path("/{documentId}/versions/{versionId}")
   public void revertDocumentVersion(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId, final @PathParam("versionId") int versionId)
         throws DbException, InvalidConstraintException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      //versionFacade.revertDocumentVersion(getInternalName(collectionName), documentFacade.readDocument(getInternalName(collectionName), documentId), versionId);
      documentFacade.revertDocument(getInternalName(collectionName), documentId, versionId);
   }

  /* @GET
   @Path("/{documentId}/rights")
   @Produces(MediaType.APPLICATION_JSON)
   public HashMap readAccessRights(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws DocumentNotFoundException, CollectionNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      return securityFacade.readRightList(getInternalName(collectionName), documentId);
   }*/

   @GET
   @Path("/{documentId}/rights")
   @Produces(MediaType.APPLICATION_JSON)
   public List<AccessRightsDao> readAccessRights(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId) throws CollectionNotFoundException, CollectionMetadataDocumentNotFoundException {
      if (collectionName == null || documentId == null) {
         throw new IllegalArgumentException();
      }
      return securityFacade.getDaoList(getInternalName(collectionName), documentId);
   }

   @PUT
   @Path("/{documentId}/rights")
   @Consumes(MediaType.APPLICATION_JSON)
   public void updateAccessRights(final @PathParam("collectionName") String collectionName, final @PathParam("documentId") String documentId, final AccessRightsDao accessRights) throws DbException, InvalidConstraintException {
      if (collectionName == null || documentId == null || accessRights == null) {
         throw new IllegalArgumentException();
      }

      final String user = userFacade.getUserEmail();
      final DataDocument document = documentFacade.readDocument(getInternalName(collectionName), documentId);

      if (securityFacade.checkForAddRights(document, user)) {
         securityFacade.setDao(getInternalName(collectionName), documentId, accessRights);
      } else {
         throw new UnauthorizedAccessException("Cannot set user rights on this document.");
      }
   }

   private String getInternalName(String collectionOriginalName) throws CollectionNotFoundException, CollectionMetadataDocumentNotFoundException {
      return collectionMetadataFacade.getInternalCollectionName(collectionOriginalName);
   }
}
