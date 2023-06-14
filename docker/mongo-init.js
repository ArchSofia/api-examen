db.createUser(
    {
      user: "usr-movie-mongo",
      pwd: "pwd-movie-mongo",
      roles: [
            {
              role: "readWrite",
              db: "movie-dev-mongo"
            }
        ]
    }
);