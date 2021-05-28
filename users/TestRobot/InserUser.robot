*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8082

*** Test Cases ***
Insert_A_User_Account
    
   create session  mysession    ${base_url}
    &{params}=     create dictionary   id=13   username=deneme  userphone=050504848   useremail=deneme@gmail.com.tr   useraddress=Catalmese Avenue   usercountry=USA   userdept=Full Stack Developer
    ${header}=    create dictionary   Content-Type=application/json
    
    ${response}=    post request  mysession   ${base_url}/AddUser   params=${params}  headers=${header}
    
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.headers}
    
*** Keywords ***