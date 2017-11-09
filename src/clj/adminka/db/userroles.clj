(ns adminka.db.userroles)

(require '[clojure.java.jdbc :as sql])

(defn create-db-user-roles [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" (
      sql/create-table-ddl :user_roles [[:id :int "not null"] [:name :text]])
    )
)

(defn insert-user-role [id name] (
  sql/insert! "postgresql://localhost:5432/adminka" :user_roles {:id id :name name}))

(defn select-user-role-all [] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from user_roles"])
  )
)

(defn get-user-role-by-id [id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from user_roles where id = ?" id])
  )
)

(defn drop-db-user-roles [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" "drop table user_roles")
)

(comment
(defn init-db-user-roles [] (
      (create-db-user-roles)
      (insert-user-role 1 "Администратор")
       (insert-user-role 2 "Дизайнер")
  )
)
)
