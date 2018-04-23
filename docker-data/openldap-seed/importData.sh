#!/usr/bin/env bash
sleep 10
ldapadd -h openldap -c -D cn=admin,dc=autentia,dc=com -w adminadmin -f /ldif_files/openldap-data.ldif
