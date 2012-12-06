package com.git.broker.impl.util;

import com.git.domain.api.IContact;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties util.
 * <p/>
 * Date: 06.12.12
 * Time: 16:49
 *
 * @author rpleshkov
 */
public class PropertiesUtil {

    private PropertiesUtil() {
        throw new AssertionError();
    }

    private static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

    /**
     * Prepare properties.
     *
     * @param contact {@link IContact}
     */
    public static void prepareProperties(IContact contact) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getResourceAsStream("/config/contact.properties"));
            prop.setProperty("contact.contactId", contact.getId());
            prop.store(new FileOutputStream(PropertiesUtil.class.getResource(
                "/config/contact.properties").getFile()), null);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
    }
}
