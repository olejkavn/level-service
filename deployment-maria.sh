MARIADB="mariadb"
echo $MARIADB
cd ..
#helm delete ${MARIADB}
#kubectl delete pvc data-${MARIADB}-0
#kubectl delete pv data-${MARIADB}-0
helm install ${MARIADB} bitnami/mariadb -f charts_bitnami/bitnami/mariadb/values.yaml
