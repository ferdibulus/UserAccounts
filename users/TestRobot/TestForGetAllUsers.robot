*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8080

*** Test Cases ***
Get_All_Users_Accounts
    
    ${response}=    GET    ${base_url}/Accounts
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.headers}
    
*** Keywords ***