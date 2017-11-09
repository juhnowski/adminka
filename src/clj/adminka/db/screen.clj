(ns adminka.db.screen)

(require '[clojure.java.jdbc :as sql])

(defn create-db-screen [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" (
      sql/create-table-ddl :screen [
      [:id :int "not null"]
      [:x :int]
      [:y :int]
      ])
    )
)

(defn insert-screen [id x y] (
  sql/insert! "postgresql://localhost:5432/adminka" :screen {:id id :x x :y y}))

(defn select-screen-all [] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from screen"])
  )
)

(defn get-screen-by-id [id] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from screen where id = ?" id])
  )
)

(defn get-screen-by-x [x] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from screen where x = ?" x])
  )
)

(defn get-screen-by-y [y] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from screen where y = ?" y])
  )
)

(defn get-screen-by-x-y [x y] (
    vector (sql/query "postgresql://localhost:5432/adminka" ["select * from screen where y = ? and x = ?" y x])
  )
)

(defn drop-db-screen [] (
  sql/db-do-commands "postgresql://localhost:5432/adminka" "drop table screen")
)

(comment
(defn init-db-screen [] (
      (create-db-screen)
      (insert-screen 1 800 400)
      (insert-screen 2      1024 600 )
      (insert-screen 3      1280 800 )
      (insert-screen 4      1024 768 )
      (insert-screen 5      800 600 )
      (insert-screen 6      1280 800 )
      (insert-screen 7      1024 768 )
      (insert-screen 8      2048 1536 )
      (insert-screen 9      1024 600 )
      (insert-screen 10      1920 1200 )
      (insert-screen 11      2560 1600 )
      (insert-screen 12      320 240 )
      (insert-screen 13      480 320 )
      (insert-screen 14      960 640 )
      (insert-screen 15      800 480 )
      (insert-screen 16      854 480 )
      (insert-screen 17      1136 640 )
      (insert-screen 18      960 540 )
      (insert-screen 19      1280 720 )
      (insert-screen 20      1280 800 )
      (insert-screen 21      1280 768   )
  )
)
