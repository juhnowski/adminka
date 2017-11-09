(ns adminka.db.users)

(require '[clojure.java.jdbc :as sql])

(defn create-db-users [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" (
      sql/create-table-ddl :users [
        [:id :int "not null"]
        [:login :text]
        [:password :text]
        [:partners_id :int]
        [:user_roles_id :int]
      ])
    )
)

(defn insert-users [id login password partners_id user_roles_id] (
  sql/insert! "postgresql://localhost:5432/adminka" :users {
    :id id
    :login login
    :password password
    :partners_id partners_id
    :user_roles_id user_roles_id
  }))

(defn select-users-all [] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from users"])
  )
)

(defn get-user-by-id [id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from users where id = ?" id])
  )
)

(defn get-users-by-partners-id [partners_id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from users where partners_id = ?"
    partners_id])
  )
)

(defn get-users-by-user-roles-id [user_roles_id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from users where user_roles_id = ?"
    user_roles_id])
  )
)

(defn get-users-by-user-roles-id-and-partners-id [user_roles_id partners_id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from users where user_roles_id = ? and partners_id = ?"
    user_roles_id
    partners_id
    ])
  )
)

(defn drop-db-users [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" "drop table users")
)

(comment
(defn init-db-users [] (
      (create-db-users)
      (insert-users 1 "super" "12345" 0 1)
      (insert-users 2 "megogo_admin" "12345" 1 1)
      (insert-users 3 "prosense_admin" "12345" 2 1)
      (insert-users 2 "megogo" "111111" 1 2)
      (insert-users 3 "prosense" "111111" 2 2)
  )
)
)
