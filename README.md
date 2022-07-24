# Meta_Chef

Description

A food delivery app.
Find Food delivery on your budget.
Hungry? Find the food you crave and order from the restaurant easily with this app. Track your food in real time. Get your food delivered using this app.

App Evaluation

- Category:Food & Drinks
- Habit: User get's rewarded discounts or coupons from time to time as the app is frequently being used.

API - Spoonacular Api (https://spoonacular.com/food-api)

Endpoints used 

Get Random Recipes - Gets random recipes from the Api to display food items on the home page
Search Recipes - Search through hundreds of thousands of recipes using advanced filtering and ranking.
Quick Answer - Search through hundreds of thousands of recipes using advanced filtering and ranking.

External Libraries

Google Materials
Glide Images

1. User (Required and Optional)

Required Must-have Stories

- User can login.
- User can create a new account.
- User can view a food and drink items.
- User can explore the variety of food.
- User can like/favorite food.
- User can search for their food choice.
- User can change profile picture.
- User can filter out their searches.

…
2. Screen Archetypes

[list first screen here]
- Login Screen
  * User can login
- Registration Screen
  * User can create a new account
- Home page
  * User can view a variety of fod items
- Search Page
 * User can search for his/her preference of food
- Cart Page
 * User can view the items added to his/her cart
- Profile Page
 * User can view their profile information
 * See the items liked/favorited
 * A quick answer feature, which the user can ask a nutrition question.
…

3. Navigation

Tab Navigation (Tab to Screen)
- Home Tab
- Search
- Cart
- Profile
Flow Navigation (Screen to Screen)
- Login Screen
 => Home
- Registration Screen
 => Home
- Home Screen 
 => Details page to view the food details
- Search Screen
 => Details Screen to view the food details
 - Search Screen
 => Filter Bottom sheet to select the filter options on your search
- Profile Screen
 => Favourites screen to view the food items liked
 - Profile Screen
 => Update Password dialog to change the users password
 
 …
4. Animations
A password shaking animation(This is triggered when the user enters an incorrect password).
A fragment to fragment slide animation.
A sliding transsition of texts in the home page.
 


<img src="https://github.com/ikechiafrics/Meta_project/blob/main/Login%20Page.png" width=500><br> 
<img src="https://github.com/ikechiafrics/Meta_project/blob/main/App%20pages.png" width=500><br>
<img src="https://github.com/ikechiafrics/Meta_project/blob/main/Whole%20app.png" width=500><br>


## Schema 
### Models
#### user

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | username        | String | User's username |
   | profileImage         | File     | image for user's profile picture |
   | createdAt     | DateTime | date when the user created account |
   | updatedAt     | DateTime | date when the user last updated the account |
   | password     | String | User's Password |
   | email     | String | User's email |
   | FirstName    | String   | User's first name |
   | LastName    | String | User's last name |
   
   
#### Cart

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | username        | String | User's username |
   | price         | Number     | each item price |
   | createdAt     | DateTime | date when the user created account |
   | updatedAt     | DateTime | date when the user last updated the account |
   | size     | Number | amount of the food  |
   | itemstotal     | Number | Total price depending on the size |
   | Image  | String   | Image of the item |
   
   
#### Food

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user (default field) |
   | user        | pointer<user>     | A pointer to the user who liked a food |
   | createdAt     | DateTime | date when the user created account |
   | updatedAt     | DateTime | date when the user last updated the account |
   | Image  | String   | Image of the item liked |
   | title    | String | Name of item liked |
   | Liked_post    | Array   | an array of likes |
