
@echo off
set "LAUNDRY_DB_USER=postgres"
set "LAUNDRY_DB_PASSWORD=azriel28"

call gradlew.bat :app:run

pause
