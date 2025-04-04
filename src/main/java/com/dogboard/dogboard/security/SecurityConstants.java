package com.dogboard.dogboard.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

public class SecurityConstants {
    public static final long JWT_EXPIRATION_TIME = 70000;
    public static final Key JWT_SECRET = secretKeyFor(SignatureAlgorithm.HS512);
}
