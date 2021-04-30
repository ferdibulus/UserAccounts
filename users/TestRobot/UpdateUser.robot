*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8080

*** Test Cases ***
Update_A_User_Account
    
    ${response}=    POST    ${base_url}/UpdateUser
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.headers}
    
*** Keywords ***