(ns adminka.db.devices)

(require '[clojure.java.jdbc :as sql])

(defn create-db-devices [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" (
      sql/create-table-ddl :partners [[:id :int "not null"] [:name :text]])
    )
)

(defn insert-partner [id name] (
  sql/insert! "postgresql://localhost:5432/adminka" :partners {:id id :name name}))

(defn select-partners-all [] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from partners"])
  )
)

(defn get-partner-by-id [id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from partners where id = ?" id])
  )
)

(defn drop-db-partners [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" "drop table partners")
)

(comment
(defn init-db-partners [] (
      (create-db-partners)
      (insert-partner 1 "Megogo")
       (insert-partner 2 "Prosense")
  )
)
)
