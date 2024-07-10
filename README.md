# Table of contents

1. [Introduction](#introduction)
2. [Backend Components](#backend-components)
3. [Frontend Components](#frontend-components)
4. [Features](#features)
5. [Bugs](#bugs)
6. [Chit Chat](#chit-chat)

## Introduction

The Local YouTube project is a Java-based application designed to replicate the core functionalities of YouTube in a local environment. This project enables users to experience video streaming, playlist management, and content discovery similar to the popular online platform, but entirely offline and localized to their personal devices.

## Backend Components

This part of the project includes 5 classes to handle the function of the project such as:

### Account Class

The Account class manages user account information, specifically handling password hashing for security purposes. It ensures that passwords are stored securely by applying cryptographic hashing algorithms, which convert passwords into irreversible strings of characters, enhancing data protection against unauthorized access.

### ServerHandler Class

The ServerHandler class acts as an intermediary between the client and the server, processing incoming requests from clients and providing appropriate responses. It manages the communication protocol, handling data transmission and ensuring seamless interaction between multiple clients and the server.

### Server Class

The Server class establishes the central server infrastructure for the Local YouTube application. It listens for incoming client connections, manages client sessions, and coordinates data exchange among connected clients. The server class implements multithreading to handle concurrent client requests efficiently and maintains the overall integrity and availability of the application.

### Client Class

The Client class represents the client-side component of the Local YouTube application. It facilitates communication with the server by sending requests, receiving responses, and displaying relevant data to the user interface. The client class manages user interactions, such as video playback requests, search queries, and playlist management, ensuring a responsive and interactive user experience.

### Database Class

The Database class handles data storage and retrieval operations for the Local YouTube application. It stores user account information, video metadata, playlists, and other relevant data in a structured format, ensuring persistent storage and efficient data management. The database class utilizes SQL or NoSQL databases to organize and query data, supporting reliable and scalable data handling capabilities.

These backend components collectively enable the Local YouTube project to deliver robust functionality, secure data management, and seamless communication between users and the application's server infrastructure. Each class plays a crucial role in ensuring the application's reliability, security, and responsiveness to user interactions.

## Frontend Components

The frontend of the Local YouTube project encompasses a diverse range of FXML components, each designed to enhance user interaction and provide a rich multimedia experience. Here are some key FXML components used in the project:

### Homepage

The Homepage serves as the central hub where users land upon logging in. It dynamically displays personalized video recommendations, trending content, and subscription updates, offering a curated browsing experience tailored to each user's preferences.
![HomePage](https://github.com/skeletknight/FINAL_AP_PROJECT_YOUTUBE/blob/front/src/main/resources/images/homepage.jpg)

### Video Player

The Video Player FXML component allows seamless playback of videos from the Local YouTube platform. It supports features such as play, pause, seek, volume control, and fullscreen mode, ensuring an immersive viewing experience for users.
![VideoPlayer](https://github.com/skeletknight/FINAL_AP_PROJECT_YOUTUBE/blob/front/src/main/resources/images/videoplayer.jpg)

### Video Uploader

The Video Uploader interface enables users to upload their videos to the platform. It includes fields for title, description, tags, and privacy settings, providing a straightforward process for content creators to share their videos with the community.
![VideoUploader](https://github.com/skeletknight/FINAL_AP_PROJECT_YOUTUBE/blob/front/src/main/resources/images/uploadpage.jpg)

### Profile

The Profile FXML provides a personalized view of user profiles. It displays user information, uploaded videos, playlists, and subscriber counts, allowing users to customize their profiles and manage their content effectively.
![ProfilePage](https://github.com/skeletknight/FINAL_AP_PROJECT_YOUTUBE/blob/front/src/main/resources/images/profilepage.jpg)
!!ج

### Musicpremium

The Musicpremium component offers a dedicated space for music enthusiasts. It features playlists, music tracks, and recommendations, with interactive elements for playing and managing music content seamlessly within the Local YouTube application.
![youtube Premium](https://github.com/skeletknight/FINAL_AP_PROJECT_YOUTUBE/blob/front/src/main/resources/images/youtubepremium.jpg)

### Other Components

Additional FXML components include:

- **Search**: Allows users to search for videos, channels, and playlists.
- **Subscription Management**: Enables users to manage their subscriptions and notifications.
- **Settings**: Provides options to customize account preferences, notifications, and playback settings.
- **Navigation Bars and Menus**: Facilitates easy navigation across different sections of the application, enhancing user accessibility and usability.

These FXML components collectively contribute to the frontend richness of the Local YouTube project, offering a diverse range of functionalities that cater to various user preferences and interaction needs. Each component is designed to ensure a seamless, engaging, and intuitive user experience within the application.

## Features

The Local YouTube project includes the following features:

- Video streaming and playback
- User account management with password hashing
- Video uploading and management
- User profile customization
- Music playback and management

## Bugs

This is a project for our advanced programing course in our first year and our second semester. Despite our efforts, the Local YouTube project may encounter occasional bugs or issues during operation. We strive to address and resolve these promptly to enhance user experience and application stability. If you encounter any issues, please report them to our support team for immediate assistance and resolution.


## Chit Chat

The development team is committed to further enhancing the Local YouTube project by adding more features, improving user interface elements, and optimizing performance. User feedback is highly valued and will be incorporated into future updates to ensure a satisfying user experience.

This report provides a comprehensive overview of the Local YouTube project, highlighting its backend and frontend components, features, and future development goals.

## Developers
Ailin Ghoreishi
Sepanat Hosseini

Mentor:
Navid Ebadi
