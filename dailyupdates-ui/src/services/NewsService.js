export const newsService = {
    getMarkhamNews: async () => {
        try {
            const res = await fetch(process.env.MARKHAM_NEWS, { cache: 'no-store' });

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return await res.json();
        } catch (error) {
            throw error;
        }
    },

    getTheHackerNewsArticles: async () => {
        try {
            const res = await fetch(process.env.HACKER_NEWS, { cache: 'no-store' });

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return await res.json();
        } catch (error) {
            throw error;
        }
    },

    getCP24LatestNews: async (category) => {
        try {
            const res = await fetch(`${process.env.CP24}${category}`, { cache: 'no-store' });

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return res.json();
        } catch (error) {
            throw error;
        }
    },

    getTopHeadlinesByCategory: async (category, page) => {
        try {
            const url = new URL("http://localhost:8080/api/newsapi/top-headlines");
            url.searchParams.append("country", "ca");
            url.searchParams.append("category", category);
            url.searchParams.append("pageSize", 20);
            url.searchParams.append("page", Number(page) > 0 ? Number(page) : 1);

            const res = await fetch(url, {cache: 'force-cache', next: {revalidate: false}});

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return res.json();
        } catch (error) {
            throw error;
        }
    }
};



