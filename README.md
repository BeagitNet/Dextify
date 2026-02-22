
---

## 📜 Key Features

### 🎬 **Cinematic Loading Screen**
- Animated Pokéball with glow, pop, and split exit
- Smooth motion and branding experience
- Navigation begins after animation completes

### 🧠 **Navigation Setup**
- Jetpack Compose Navigation Host
- Named routes (loading → list → detail)
- Safe backstack behavior (popUpTo)

### 📄 **Pokémon List Screen**
- Fetches data from PokéAPI
- Shows sprite + name list
- State handling with ViewModel + StateFlow
- Navigation on click

### 📊 **Pokémon Detail Screen**
- Header with name, ID, favorite icon
- Scrollable body
- Footer tab navigation (Evolution / Details / Movesets)
- API-driven data rendering

### 🗂 **Adaptive Icons & Branding**
- Custom adaptive icon with Pokéball theme
- Vector design with background effects

---

## 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| UI | **Jetpack Compose** |
| DI | **Hilt (Dagger dependency injection)** |
| Networking | **Retrofit + OkHttp** |
| Concurrency | **Kotlin Coroutines** |
| Image Loading | **Coil** |
| Navigation | **Compose Navigation** |
| JSON Parsing | **Gson / Kotlin serialization** |
| Local Storage | (Optional) **Room Database** |
| Build System | **Gradle (Kotlin DSL)** |
| Language | **Kotlin** |
| Design System | **Material 3** |

---

## 📡 API Integrations

### 🧾 PokéAPI Endpoints Used

| Endpoint | Purpose |
|----------|---------|
| `/pokemon/{id or name}` | Pokémon base data (sprites, stats, moves, types) |
| `/pokemon-species/{id or name}` | Species data (flavor text, evolution chain URL) |
| `/evolution-chain/{id}` | Evolution chain data |

All data is fetched asynchronously using Retrofit within ViewModels powered by coroutines.

---

## 🚀 Getting Started

### 🔹 Prerequisites

✔ Android Studio Bumblebee or newer  
✔ Kotlin 1.8+  
✔ Minimum SDK API 24+  
✔ Internet connection (for API calls)

---

### 📦 Install & Run

1. Clone the repository:
    ```bash
    git clone https://github.com/<your-username>/Dextify.git
    ```
2. Open the project with **Android Studio**  
   → Let Gradle sync  
3. Build & run on an emulator or device  
   → Splash screen should show  
4. Navigate through list and detail screens

---

## 🧪 Project Structure Explained

### 🟦 Loading Screen

- Located in `presentation/loadingscreen/`
- Plays the splash animation
- Optional branding text
- Navigates to main list after animation

### 🟩 Pokémon List Screen

- Located in `presentation/screen/pokemon_list/`
- Uses ViewModel to fetch API data
- Shows scrolling list of Pokémon
- Pressing any item navigates to detail

### 🟨 Pokémon Detail Screen

- Located in `presentation/screen/pokemon_detail/`
- Advanced UI with:
  - Fixed header
  - Scrollable content
  - Tabbed footer
  - Favorite marking
- Pulls data using ViewModel + API calls

### 🟪 Navigation

- Located in `presentation/navigation/`
- `NavHost` with `composable()` routes
- Routing between loading → list → detail

---

## 📌 What You’ll Learn by Exploring This App

If you’re studying or contributing, here’s what you’ll pick up:

✔ Modern UI with Jetpack Compose  
✔ Multi-screen navigation with Compose  
✔ Dependency injection with Hilt  
✔ Retrofit API integration  
✔ Asynchronous programming with coroutines  
✔ Clean separation of UI + business logic  
✔ Animated transitions & motion design  
✔ Adaptive launcher icons and resources  
✔ Tabbed UI and scroll interplay

---

## 🧾 Contributing

I welcome contributions 💛
1. Fork the repo  
2. Create a branch (feature/your-feature)  
3. Commit your changes  
4. Open a pull request

Suggested areas:
✔ Search and filter UI  
✔ Favorites persistence (Room)  
✔ Dark mode / theme switching  
✔ UI performance improvements

---

## 🎨 Future Roadmap

Planned enhancements:

- Pagination on Pokémon list
- Offline data caching with Room
- Search & filter combos
- Custom animations per Pokémon
- Localization support
- Accessibility improvements

---

## 📄 License

This project is open source under the **MIT License**.

---

## 🙏 Acknowledgements

- Built by **BeagitNet**
- Inspired by **Pokémon series**
- API provided by **PokéAPI**
- UI guided by **Material 3 design**

---
