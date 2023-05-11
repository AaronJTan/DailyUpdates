import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getTheHackerNewsArticles} from "../../services/NewsService.js"
import "./styles.css"

export default async function TheHackerNews() {
    const headlines = await getTheHackerNewsArticles();

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
                {headlines.data.map((headline) => 
                    <Headline headline={headline} />
                )}
            </CardBody>
        </Card>
    );
}