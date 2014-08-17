package com.searchly.discovery.etcd;

import com.carrotsearch.randomizedtesting.annotations.TestGroup;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.FailedToResolveConfigException;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ElasticsearchIntegrationTest;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ferhat
 */
public abstract class EtcdAbstractTest extends ElasticsearchIntegrationTest {

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @TestGroup(enabled = false, sysProperty = SYSPROP_ETCD)
    public @interface EtcdTest {
    }

    public static final String SYSPROP_ETCD = "tests.etcd";

    @Override
    protected Settings nodeSettings(int nodeOrdinal) {
        ImmutableSettings.Builder settings = ImmutableSettings.builder()
                .put(super.nodeSettings(nodeOrdinal))
                .put("plugins." + PluginsService.LOAD_PLUGIN_FROM_CLASSPATH, true);

        Environment environment = new Environment();

        // if explicit, just load it and don't load from env
        try {
            if (Strings.hasText(System.getProperty("tests.config"))) {
                settings.loadFromUrl(environment.resolveConfig(System.getProperty("tests.config")));
            } else {
            }
        } catch (FailedToResolveConfigException exception) {
        }
        return settings.build();
    }
}
