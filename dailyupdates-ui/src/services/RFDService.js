export default async function getRFDHotDeals() {

    const res = await fetch('http://localhost:8080/hot-deals', { cache: 'no-store' });
    // The return value is *not* serialized
    // You can return Date, Map, Set, etc.
    
    // Recommendation: handle errors
    if (!res.ok) {
        // This will activate the closest `error.js` Error Boundary
        throw new Error('Failed to fetch data');
    }
    
    return res.json();
}