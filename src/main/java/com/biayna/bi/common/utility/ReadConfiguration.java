package com.biayna.bi.common.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class ReadConfiguration {

    private ClassLoader objClassLoader = null;
    private Properties commonProperties = new Properties();
    private static final String directory = "configuration";
    
    public ReadConfiguration() {
        /**
         *  Initialize 'objClassLoader' once so same 
         *  object used for multiple files. 
         */
        objClassLoader = getClass().getClassLoader();
    }
    
    /**
     * Reads property file name and keys
     * return string value assigned to the key
     * 
     * @param propertiesFilename
     * @param key
     * @return
     */
    public String readKey(String propertiesFilename, String key) {
        if (propertiesFilename != null && !propertiesFilename.trim().isEmpty()
                && key != null && !key.trim().isEmpty()) {
        	FileInputStream objFileInputStream = null;
            try{
            	File file = new File(objClassLoader.getResource(directory+File.separator+propertiesFilename).toURI());
            	objFileInputStream = new FileInputStream(file);
                /* Load file into commonProperties */
                commonProperties.load(objFileInputStream);
                /* Get the value of key */
                return commonProperties.getProperty(key);            
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException e) {
				e.printStackTrace();
			} finally {
            	try {
					objFileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return null;
    }
}