package com.gnose.mvp.Documents_Module.Application;

public interface IDocumentsBlobService {
    String getDocumentBlobUrl(String fileName);
    String createDocumentBlob(String fileName);
}
