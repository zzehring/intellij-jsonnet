local env = "production";

{
  debug: if env == "development" then true else false,
  logLevel: if env == "production" then "error" else "debug"
}
