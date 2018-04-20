#!/usr/bin/env bash
sleep 10
ldapadd -h openldap -c -D cn=admin,dc=tsers,dc=org -w admin -f /ldif_files/openldap-data.ldif
