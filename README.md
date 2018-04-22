# RMA - DZ3 - Tasky
#### *A simple to-do list android application*

Tasky is an application for a simple to-do task tracking. As the user starts the app, the main screen - task list - is displayed. On the touch of a floating action button, a new screen is displayed. The user enters a task title and optionally a description, category and a deadline. Task priority is also asigned and the user can choose between a low, medium and high task priority which results in a different task appearance on the main task list screen. With a signle tap (click) user can se the description of a task in a Toast message, while on a long press the user can delete the task.

### The assignment and problems encountered
The assignment was to create an android app which uses a simple database for creating user tasks (to-do tasks). A user must be enabled to create new tasks, save them in the database, view them in a task list and delete them.
The first step was to create the ListActivity with a RecyclerView where a list of tasks from the database would be displayed. Also, a **Task** class defining the data model (entity) was created, together with a **Task Adapter** that connects data and the RecyclerView. At first, an approach with DBHelper and SQLiteOpenHelper was used, which turned out to be rather complicated and messy. Therefore a different approach was applied during the development phase - using **Room**. This included the creating of different files, such as:
**TaskDao** (defining the data access object with necessary SQL queries), **TaskRoomDatabase** (a layer ontop of the SQLite Database that uses TaskDao to issue queries to the database), **TaskRepository** (used for managing multiple data sources) and a **TaskViewModel** (feeds data to the UI).

A huge help in the development of the app, using the Room, was Google's Codelabs tutorial "Android Room with a View" which explained the very concept of database handling and working with Room, as well as a detailed instructions on building an app with a RecyclerView and a database.

## Utilised snippets/solutions/libraries/SO answers

[ButterKnife](http://jakewharton.github.io/butterknife/)

[RecyclerView dependencies](https://developer.android.com/topic/libraries/support-library/packages.html)

[Room dependecies](https://developer.android.com/topic/libraries/architecture/adding-components.html)

[Android Support Library: *com.android.support:design:26.1.0*](https://developer.android.com/topic/libraries/support-library/packages.html)

[Google Codelabs "Android Room with a View" tutorial"](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..%2Findex#0)

<div>
 <img src="/screenshots/emptyState.png" alt="Conversion Choosing from Spinner" width = "216" height = "384" />
 <img src="/screenshots/newTaskDeleted.png" alt="List Activity" width = "216" height = "384" />
 <img src="/screenshots/missingTaskTitle.png" alt="Conversion Activity 1" width = "216" height = "384" />
 <img src="/screenshots/priorityChoice.png" alt="Conversion Activity 3" width = "216" height = "384" />
 <img src="/screenshots/newTaskFilled.png" alt="Conversion Activity 2" width = "216" height = "384" />
 <img src="/screenshots/newTaskAdded.png" alt="Conversion Activity 4" width = "216" height = "384" />
 <img src="/screenshots/showDescription.png" alt="Conversion Button Click while Empty input" width = "216" height = "384" />
 </div>
 
 ### Completed/Didn't completed and reasons
 The app provides all of the minimum required things. In addition, an empty state was added and some minor improvements. However, the app lacks secure database handling, better UI, more functionalities etc.
 Also, a Room aproach was used, so the DBHelper file was deleted as it was not necessary.
 
 1. [x] Created List activity and fill it from database
 2. [x] Created Task class with a few attributes
 3. [x] List element layout designed
 4. [ ] DBHelper (initially was done - first commit, but not used afterwards)
 5. [x] New Tast Activity created and designed
 6. [x] Long press item deleting implemented
 
  ### Comment (optional)
 As it was mentioned previously, the most challenging part was to connect with the database. At first, DBHelper was used with all the necessary code for it to run successfully, which turned out to be complicated and messy. Also, the first version would crash after restarting the app. This is the exact reason for switching to Room in database handling. As mentioned, Google's Codelabs tutorial was an enormous help as I learned some info on the app architecture as well as how to work with Room (the basics at least). There is a lot of space for improvements in this app, from applying more MVP structure to adding functionalities and a better UI/UX.
