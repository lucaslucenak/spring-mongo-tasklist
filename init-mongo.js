use admin
db.createUser(
  {
    user: "root",
    pwd: "pass123",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]
  }
)

use tasklist