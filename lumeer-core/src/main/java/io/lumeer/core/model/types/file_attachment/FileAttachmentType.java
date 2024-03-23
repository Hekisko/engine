package io.lumeer.core.model.types.file_attachment;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;

public class FileAttachmentType extends AbstractType {

    public FileAttachmentType() {
        super(EAiType.FILE_ATTACHMENT);
    }

    @Override
    public Object getConstraints() {
        return new HashMap<>();
    }
}
