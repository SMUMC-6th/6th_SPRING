package com.example.umc.study.dto;

import lombok.Getter;

public class KakaoDTO {

    @Getter
    public static class OAuthToken {
        String token_type;
        String access_token;
        String refresh_token;
        Long expires_in;
        Long refresh_token_expires_in;
        String scope;
    }

    @Getter
    public static class KakaoProfile {
        private Long id;
        private String connected_at;
        private Properties properties;
        private KakaoAccount kakao_account;

        @Getter
        public class Properties {
            private String nickname;
            private String profile_image;
            private String thumbnail_image;
        }

        @Getter
        public class KakaoAccount {
            private String email;
            private Boolean is_email_verified;
            private Boolean email_needs_agreement;
            private Boolean has_email;
            private Boolean profile_nickname_needs_agreement;
            private Boolean profile_image_needs_agreement;
            private Boolean email_needs_argument;
            private Boolean is_email_valid;
            private Profile profile;

            @Getter
            public class Profile {
                private String nickname;
                private String thumbnail_image_url;
                private String profile_image_url;
                private Boolean is_default_nickname;
                private Boolean is_default_image;
            }
        }
    }

}