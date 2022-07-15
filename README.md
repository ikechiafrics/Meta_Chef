# Meta_Chef
Description
A food delivery app.
Find Food delivery on your budget.
Hungry? Find the food you crave and order from the restaurant easily with this app. Track your food in real time. Get your food delivered using this app.

App Evaluation

[Evaluation of your app across the following attributes]

- Category:Food & Drinks
- Habit: User get's rewarded discounts or coupons from time to time as the app is frequently being used.

1. User (Required and Optional)

Required Must-have Stories

- User can login
- User can create a new account
- User can view a food and drink items
- User can explore the variety of food
- User can view their profile information
- User can like/favorite food
- User can search for their food choice
- User can filter out their searches.
- User can see their profile page with their profile picture

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
 * add/remove items in the cart
…

3. Navigation

Tab Navigation (Tab to Screen)
- Home Tab
- Search
- cart
Flow Navigation (Screen to Screen)
- Login Screen
 => Home
- Registration Screen
 => Home
- Home Screen 
 => Profile Screen from a user's post 
- Search Screen
 => Another screen to show posts related to the game selected
- Creation Screen
 => Home (after you finish posting the photo)
- Search Screen
 => Profile Screen of the user searched
- Menu Item nav
 => user's profile
 => settings screen


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
