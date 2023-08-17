# Daily Updates
**Disclaimer**: This application is a personal project and is not affiliated with RedFlagDeals.com or any news sources presented on the site.

## Description
Daily Updates is a web application that provides a convenient and centralized platform to access articles from various news websites and the latest deals posted on RedFlagDeals.com. This application eliminates the need to visit multiple websites separately, offering a seamless experience for staying updated on both news and deals.

The articles and deals are presented on a single, easy to navigate page. Just click on any news headline or deal title to be redirected to the original source.

Current sources:
- [RedFlagDeals.com Hot Deals](https://forums.redflagdeals.com/hot-deals-f9/)
- [YorkRegion.com Markham News](https://www.yorkregion.com/ontario-communities/markham/)
- [CP24 Local Top Stories](https://www.cp24.com/news)
- [CP24 World News](https://www.cp24.com/world)
- [The Hacker News](https://thehackernews.com/)


## Built With
* [![Spring Boot][Spring Boot]][Spring-Boot-url]
* [![Next][Next.js]][Next-url]
* [![Tailwind][Tailwind CSS]][Tailwind-url]

<!-- MARKDOWN LINKS & IMAGES -->
[Spring Boot]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-Boot-url]: https://spring.io/projects/spring-boot
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/

[Tailwind CSS]: https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white
[Tailwind-url]: https://tailwindcss.com/


## Getting Started
### Step 1. Clone this repository
```bash
git clone https://github.com/AaronJTan/DailyUpdates.git
cd DailyUpdates
```
### Step 2. Running the application
Option 1: Run using Docker
```bash
docker compose up -d
```

Option 2: Run locally using Node.js, npm and Maven

In one terminal window:
```bash
cd dailyupdates-ui
npm run dev
```

In another terminal window:
```bash
cd DailyUpdates
mvn spring-boot:run
```

### Step 3: Accessing the website
Access the website at [http://localhost:3000](http://localhost:3000).