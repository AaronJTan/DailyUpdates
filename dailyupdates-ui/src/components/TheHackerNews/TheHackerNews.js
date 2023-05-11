import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getTheHackerNewsArticles} from "../../services/NewsService.js"
import "./styles.css"
import FetchError from "../ErrorMessages/FetchError";

export default async function TheHackerNews() {
    let error = false;
    let headlines;

    try {
        headlines = await getTheHackerNewsArticles();
    } catch (err) {
        error = true;
    }

    const Headline = ({headline}) => {
        return (
            <div className="link-item">
                <a href={headline.url} target="_blank">
                    {headline.headline}
                </a>
            </div>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                The Hacker News
            </CardTitle>
            <CardBody>
                {
                    error ? <FetchError /> :
                
                    headlines.data.map((headline) => 
                        <Headline headline={headline} />
                    )
                }
            </CardBody>
        </Card>
    );
}