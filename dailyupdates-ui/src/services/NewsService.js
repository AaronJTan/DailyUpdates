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
    }
};



