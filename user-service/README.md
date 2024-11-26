# user-authentication-authorization-manager
A authentication &amp; authorization service provide user registration and user authorization.

## Environment Variables with default values:
* DATABASE_USERNAME: admin
* DATABASE_PASSWORD: password
* DATABASE_HOSTNAME: 127.0.0.1
* DATABASE_PORT: 5432
* DATABASE_NAME: postgres
* SERVER_PORT: 9532
* SECRET_KEY: cromxt
* EXPIRATION: 900000 [ Access Token expires in 15 minutes ]
* REFRESH_EXPIRATION: 1296000000 [ Refresh Token expires in 15 days ] 
* API_KEY: cromxt
* IS_SECURE: false [ Set true if you want to use https ]