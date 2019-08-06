# Authentication in web service


## Introduction

You joined team implementing authentication to web service. 
Existing tests are not longer passing, and there is also new functionality that has to be tested.

Test system contains 2 registered users that can log in:
| username | password |
| --- | --- |
| david | david1 |
| harry | harry1 |

## Problem statement

1. test `testUserDavidCanLogIn` is filing because:
    * selenium can't find login button because element locator has changed. Element can be now found by name = 'btn_login' Update login button locator
     in class `LoginPage`
    * login process now takes about 2 seconds. Make sure assertion that user has logged in waits long enough before failing.

2. test `testLoginWithInvalidCredentials` is failing because error message has changed. Find out what is the new error message and update test.

3. test `testLoginWithNoUsername` is not implemented. It should assert that error message will be equal to 'Username is required' 
if user try to login with only password provided.
Implement this test. It will look similar to `testLoginWithNoPassword` 
 
4. test `testUsernameDisplayed` is not implemented. It should check displayed username in dashboard page is same as provided on login 
page during. Test contains described steps in comments you need to implement. 
   
5. test `testUnauthorizedUserCantSeeDashboard` is failing because it has invalid assertion. 
Find out what is wrong in this assertion and fix it.




