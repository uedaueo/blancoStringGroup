/*
 * blanco Framework
 * Copyright (C) 2004-2006 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.stringgroup.task;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import blanco.stringgroup.BlancoStringGroupConstants;
import blanco.stringgroup.BlancoStringGroupMeta2Xml;
import blanco.stringgroup.BlancoStringGroupXml2SourceFile;
import blanco.stringgroup.message.BlancoStringGroupMessage;
import blanco.stringgroup.task.valueobject.BlancoStringGroupProcessInput;

/**
 * Generates class source code to process string group from "String Group Definition" Excel format.
 *
 * @author IGA Tosiki
 */
public class BlancoStringGroupProcessImpl implements BlancoStringGroupProcess {
    /**
     * Message definition.
     */
    private final BlancoStringGroupMessage fMsg = new BlancoStringGroupMessage();

    public int execute(final BlancoStringGroupProcessInput input)
            throws IOException, IllegalArgumentException {
        System.out.println("- " + BlancoStringGroupConstants.PRODUCT_NAME
                + " (" + BlancoStringGroupConstants.VERSION + ")");

        try {
            final File fileMetadir = new File(input.getMetadir());
            if (fileMetadir.exists() == false) {
                throw new IllegalArgumentException(fMsg.getMbsga001(input
                        .getMetadir()));
            }

            // Creates a temporary directory.
            new File(input.getTmpdir()
                    + BlancoStringGroupConstants.TARGET_SUBDIRECTORY).mkdirs();

            // Processes the specified meta directory.
            new BlancoStringGroupMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoStringGroupConstants.TARGET_SUBDIRECTORY);

            // Generates source code from XML-ized intermediate files.
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoStringGroupConstants.TARGET_SUBDIRECTORY)
                    .listFiles();
            for (int index = 0; index < fileMeta2.length; index++) {
                if (fileMeta2[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                final BlancoStringGroupXml2SourceFile xml2source = new BlancoStringGroupXml2SourceFile();
                xml2source.setEncoding(input.getEncoding());
                xml2source.process(fileMeta2[index], input.getTargetlang(),
                        new File(input.getTargetdir()));
            }

            return BlancoStringGroupBatchProcess.END_SUCCESS;
        } catch (TransformerException ex) {
            throw new IOException("An exception has occurred during the XML conversion process: " + ex.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean progress(final String argProgressMessage) {
        System.out.println(argProgressMessage);
        return false;
    }
}
