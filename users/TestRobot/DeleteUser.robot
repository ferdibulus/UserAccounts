*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8082

*** Test Cases ***
Delete_A_User_Account
    
  create session  mysession    ${base_url}
    &{params}=     create dictionary       id=1
    ${header}=    create dictionary   Content-Type=application/json
    
    ${response}=    post request  mysession   ${base_url}/DeleteUser   params=${params}  headers=${header}
    
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.headers}
    
    
*** Keywords ***