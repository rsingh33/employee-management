package com.home.employeemanagement.micrometerconfig;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration
public class MicrometerConfig {

    AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
        @Override
        public String apiToken() {
            return "YOFsDngv-HMpE-t_HGJNfg-OeBrP901xSp3V4S4R4Sq-fmVtRN662-N7y1sz-EfISgXlW6U";
        }

        @Override
        @Nullable
        public String get(String k) {
            return null;
        }
    };
    MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);
}
