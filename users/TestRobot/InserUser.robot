*** Settings ***
Library     RequestsLibrary
Library     Collections
*** Variables ***
${base_url}       http://localhost:8080

*** Test Cases ***
Insert_A_User_Account
    create session  mysession    ${base_url}
    ${body} =     create dictionary   id="13434"   username="deneme"   userphone="050504848"   useremail="deneme@gmail.com.tr"   useraddress=""   usercountry="USA"   userdept="AUS""
    ${header}=    create dictionary   Content-Type=application/json
    
    ${response}=    post request  mysession   ${base_url}/AddUser   data=${body}  headers=${header}
    
    log to console  ${response.status_code}
    log to console  ${response.content}
    log to console  ${response.headers}
    
*** Keywords ***