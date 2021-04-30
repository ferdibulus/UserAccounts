*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8080

*** Test Cases ***
Delete_A_User_Account
    
    ${response}=    POST    ${base_url}/DeleteUser
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.header
    
*** Keywords ***
s}