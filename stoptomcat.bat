call %CATALINA_HOME%\bin\shutdown.bat
if "%ERRORLEVEL%" == "0" goto end
echo.
goto fail

:fail
echo.
echo tomcat is offline

:end
echo.
echo Work is finished.

