export const dealsService = {
    getRFDHotDeals: async () => {
        try {
            const res = await fetch(process.env.RFD, { cache: 'no-store' });

            if (!res.ok) {
                throw new Error('Failed to fetch data');
            }

            return await res.json();
        } catch (error) {
            throw error;
        }
    }
}