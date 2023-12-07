package com.bhurb.payments.presentation.controller;

public class ApiInfo {

    public static final String API_V1 = "/api/v1";

    private ApiInfo() {
    }

    public static class StatusCode {

        public static final String OK = "200";
        public static final String BAD_REQUEST = "400";

        private StatusCode() {
        }
    }

    public static class Tags {

        public static final String PUBLIC = "Api Public";
        public static final String PAYMENTS = "Payments";

        private Tags() {
        }
    }
}
