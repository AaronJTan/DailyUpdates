export const nbaService = {
    getStandings: async () => {
        try {
            const res = await fetch("http://localhost:8080/nba", { cache: 'no-store' });

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return await res.json();
        } catch (error) {
            throw error;
        }
    }
}