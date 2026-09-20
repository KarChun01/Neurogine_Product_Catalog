# Neurogine Product Catalog

An Android product catalog application built as part of the Neurogine Junior Mobile Developer technical assessment.

The app retrieves product data from the DummyJSON API and provides product browsing, pagination, server-side search, and product details.

## Features

* Product catalog displaying:

    * Product title
    * Product thumbnail
    * Product price
* Infinite scrolling pagination
* Debounced product search
* Product detail screen
* Product description, price, rating, and image
* Loading states
* Error states with Retry
* Empty search results state
* Image loading placeholder and error fallback
* Back navigation between catalog and product detail

## Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Architecture:** MVVM
* **Networking:** Retrofit
* **JSON Serialization:** Gson
* **Image Loading:** Coil
* **API:** DummyJSON

## Architecture

The application follows a lightweight MVVM architecture with two main layers: UI and Data.

```text
┌──────────────────────┐
│     Compose UI       │
│                      │
│ ProductListScreen    │
│ ProductDetailScreen  │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│      ViewModel       │
│                      │
│ ProductListViewModel │
│ ProductDetailVM      │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│      Repository      │
│      ProductRepo     │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│       Retrofit       │
│     ProductApi       │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│      DummyJSON       │
└──────────────────────┘
```

### Project Structure

```text
app/
├── data/
│   ├── model/
│   │   ├── Product.kt
│   │   └── ProductResponse.kt
│   ├── remote/
│   │   └── ApiInstance.kt
│   │   └── ProductApi.kt
│   └── repo/
│       └── ProductRepo.kt
│
├── ui/
│   ├── lists/
│   │   ├── ProductListScreen.kt
│   │   ├── ProductListViewModel.kt
│   │   └── ProductListState.kt
│   │
│   ├── details/
│   │   ├── ProductDetailScreen.kt
│   │   ├── ProductDetailViewModel.kt
│   │   └── ProductDetailState.kt
│   │
│   └── theme/
│
└── MainActivity.kt
```

## API

The application uses the [DummyJSON](https://dummyjson.com/) product API.

### Product List

```text
GET /products?limit={limit}&skip={skip}
```

Pagination is implemented using the API's `limit` and `skip` parameters.

The app loads an initial page of products and requests additional products when the user scrolls near the bottom of the list.

### Product Details

```text
GET /products/{id}
```

The selected product ID is passed to the `ProductDetailViewModel`, which retrieves the product details through the repository.

### Search

```text
GET /products/search?q={query}&limit={limit}&skip={skip}
```

The application uses DummyJSON's server-side search endpoint rather than downloading the entire product catalog and filtering it locally.

Search input is debounced by **500 ms** before making a network request. This reduces unnecessary API requests while the user is typing.

Search pagination uses the same `skip` mechanism as the normal product list.

## UI States

The application handles the following states:

* **Loading** — displays a loading indicator while data is being retrieved.
* **Success** — displays the retrieved products or product details.
* **Empty** — displays an appropriate message when a search returns no products.
* **Error** — displays an error message and Retry button when a request fails.
* **Loading More** — displays a loading indicator while additional products are being loaded.

Images also provide a placeholder for loading and failed image requests.

## How to Run

1. Clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync and download dependencies.
4. Connect an Android device or start an Android emulator.
5. Run the `app` configuration.

## Requirements

- Android Studio
- Android SDK
- Internet connection
- Android device or emulator

## Design Decisions

### Why MVVM?

MVVM separates UI rendering from application state and data-fetching logic.

The Composable screens are responsible for displaying state, while the ViewModels handle user actions and coordinate data requests through the repository.

### Why a Repository?

The repository provides a single data-access layer between the ViewModels and Retrofit.

This keeps API-specific implementation details out of the UI layer and makes the ViewModels easier to understand and test.

### Why Server-Side Search?

DummyJSON provides a dedicated search endpoint, so the application uses it instead of downloading all products and filtering them locally.

This keeps the search implementation simple and avoids unnecessarily transferring the complete product dataset.

### Why No Domain / Use Case Layer?

The application is intentionally kept lightweight for the scope of this assessment.

The current requirements do not require complex business logic, so adding a separate domain/use-case layer would add additional abstraction without providing significant value for this project.

## TODOs / Unfinished Work

The project was developed within the requested approximately 2–3 hour assessment timebox. The following items were intentionally left as future improvements rather than extending the scope beyond the timebox:

* [ ] **Pull-to-refresh** — Add swipe-to-refresh support for the product catalog.
* [ ] **Unit tests** — Add unit tests for ViewModel state handling, pagination, search debounce, and error scenarios.
* [ ] **Local caching** — Add local persistence/caching to allow previously loaded product data to be available without an active network connection.
* [ ] **Improved navigation** — Replace the current lightweight screen switching with a dedicated navigation solution if the application were expanded further.
* [ ] **Additional UI polish** — Further refine animations, accessibility, and responsive layouts for a production application.

These items were intentionally not implemented to keep the implementation within the assessment timebox and to prioritize the required functionality.

## AI Usage & Development Accountability

This project was developed as my own work, with AI used only as a supporting tool for guidance, research, and troubleshooting.

### Minimal Usage

AI assistance was used for:

* Researching and clarifying Android/Kotlin concepts when needed.
* Reviewing implementation approaches and identifying potential issues.
* Getting guidance on UI/UX improvements and edge cases.

AI was **not used to independently design or generate the complete application**.

### Original Work

The following were my own development decisions and implementation responsibilities:

* Overall project structure
* MVVM architecture
* Separation between UI, ViewModel, Repository, and Retrofit API layers
* API and pagination approach
* Server-side search approach
* State management design
* Feature implementation
* UI design and screen structure
* Navigation flow
* Error, loading, and empty-state handling

The final code was reviewed, understood, tested, and adapted by me during development.

### Accountability

I understand and can explain the code and implementation decisions presented in this repository.

During the walkthrough, I will be able to explain the relevant code, including:

* How the Compose UI observes ViewModel state.
* How the ViewModels handle user actions and API requests.
* How the Repository communicates with Retrofit.
* How pagination uses `skip` and `limit`.
* How debounced search works.
* How product detail data is loaded.
* How loading, success, empty, and error states are handled.
* Why the application uses a lightweight MVVM architecture without a separate domain/use-case layer.

AI assistance was used as a supporting resource, but responsibility for understanding, testing, and explaining the submitted implementation remains with me.

## Assessment Scope

This project was developed within the provided assessment timebox, with emphasis on:

* Clear architecture
* Functional product browsing
* Pagination
* Search
* Product details
* Error and loading state handling
* Maintainable and understandable Kotlin code
* Progressive Git history
