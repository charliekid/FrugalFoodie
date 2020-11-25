# Frugal Foodie Application


## Project Description

Frugal Foodie is an application that generates recipes based on ingredients or weekly sale ads. 
When using the weekly sale ad feature, the application will produce the least expensive recipe for the user. 

This application addresses a few different issues. First is the issue of the amount of time people 
spend planning for their weekly meals. For those on budget, buying items on sale can save their 
household a lot of money. One could spend hours looking through ads and coordinating it with their 
cookbook. This application will also save the user time on deciding on which recipe to make. 
Eliminating indecisiveness.  

## Project Requirements

- [ ] Must use Git
- [ ] Must use Git project boards
- [ ] Must have persistent data

- [ ] Multiple access level (e.g. a normal user and an admin user)
- [ ] Must have a central location to allow users to share information. e.g. if userA logs in and posts a picture, userB should be able to login and view that picture. This can be accomplished by using creating a web API or by using a service like Firebase


## Project Board
https://github.com/users/charliekid/projects/1 

## Iteration 1 ()

**User Story**: Login - Emory
> As a user I want to login so I can have my own account

**How will it be tested**:
- [ ] Unit tests for username verification and password
- [ ] Instrumented test to verify extras

Notes: 
- This is necessary for the rest of the app to function so it is the highest priority

***

**User Story**: Create Account - Cathy 
> As a user, I want to be able to create a new account so that I can use that I can use the app

**How will it be tested**:
- [x] Test to see if user account gets added into the DAO

Notes: 
- Needed for new users, second highest priority 

***

**User Story**: Database - Debbie / Cathy 
> Set up the database.

**How will it be tested**:
- [ ] Create a test where we ensure items are CRUDing on the database. 

Notes: 
- Debbie will set up the skeleton of the DB and Cathy will help with queries / model classes
***

**User Story**: Webscrape - Charlie
> As a user I want to be able to select ingredients that are on sale

**How will it be tested**:
- [ ] Check against the txt file and ensure data is correct

Notes: 
- Backend
***
## Iteration 2 ()

**User Story**: View All Ingredients (from the ads) - Cathy
> As a user I want to search weekly store ads so I can accomplish searching for a recipe based off of sale items.

**How will it be tested**:
- [x] This is a UI. No test required.

Notes: 
- Front End - RecyclerView
***

**User Story**: Hard Code Recipes 
> Add recipes to the Database

**How will it be tested**:
- [ ] Create a test where we get recipes from the database 

Notes: 
- Backend
***

## Iteration 3 ()

## Iteration 4 ()

## User Stories


**User Story**: View All Recipes - Emory
> As a user I want to view recipes after search so I can accomplish picking a recipe I want

**How will it be tested**:
- [ ] Test UI for recyclerview (?)
- [ ] Test to see if when an individual recipe is clicked if individual recipe activity will show

Notes: 
- All results from the search
- RecyclerView
***

**User Story**: View Individual Recipes - Debbie
> As a user I want to view a specific recipe after search so I can view the directions for the recipe.

**How will it be tested**:
- [ ] This is a UI. No test required.

Notes: 
- Displays a particular recipe's information
***

**User Story**: View Upload - Emory
> As a user I want to view a page where I can upload my own recipe

**How will it be tested**:
- [ ] This is a UI. No test required.

Notes: 
- Front end
- XML so that user can input recipe texts
***

**User Story**: Upload Recipe to DAO - Cathy
> As a user I want to upload a recipe so I can accomplsh sharing my recipe with other users.

**How will it be tested**:
- [ ] Check to see if the query is working

Notes: 
- Backend
***

**User Story**: Search By Ingredients (from the ads) - Charlie
> As a user I want to search weekly store ads so I can accomplish searching for a recipe based off of sale items

**How will it be tested**:
- [ ] DAO test to see if query worked

Notes: Back end - Recycler View?
***

**User Story**: Admin - Charlie
> As an admin I want to be able to input weekly sales ad into the database so that user can search for new ingredients 

**How will it be tested**:
- [ ] If newly sale items are updated on the search by ingredients page

Notes: 
- Only the admin has the ability to input weekly sales
***

## Mockups
![mockup](/img/frugalfoodmockup.PNG)

## UMLs and ERDs
![uml](/img/umlerd.PNG)
