export const getMarkhamNews = async() => {

    const res = await fetch('http://localhost:8080/news/markham');
    // The return value is *not* serialized
    // You can return Date, Map, Set, etc.
    
    // Recommendation: handle errors
    if (!res.ok) {
        // This will activate the closest `error.js` Error Boundary
        throw new Error('Failed to fetch data');
    }
    
    return res.json();
}

export const getTheHackerNewsArticles = async() => {

    const res = await fetch('http://localhost:8080/thehackernews/latest-news');

    if (!res.ok) {
        throw new Error('Failed to fetch data');
    }
    
    return res.json();
}

export const getCP24LatestNews = async(category) => {
    const res = await fetch(`http://localhost:8080/cp24/${category}`);

    if (!res.ok) {
        throw new Error('Failed to fetch data');
    }
    
    return res.json();
}
