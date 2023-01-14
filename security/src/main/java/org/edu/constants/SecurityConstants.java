package org.edu.constants;

public class SecurityConstants {
  public static final String SECRET_KEY = "zzd";
  public static final long EXPIRATION_TIME = 12*30 *24 * 60 * 60 *1000L; //有效期
  public static final String TOKEN_PREFIX = "Bearer";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/api/services/controller/user";
}