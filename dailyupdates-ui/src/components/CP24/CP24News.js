import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getCP24LatestNews} from "../../services/NewsService.js"
import { cp24Category } from "./categories";
import FetchError from "../ErrorMessages/FetchError";

export default async function CP24News({category}) {
    let error = false;
    category = category || cp24Category.LATESTNEWS;
    let headlines;

    try {
        headlines = await getCP24LatestNews(category.path);
    } catch (err) {
        error = true;
    }

    const Headline = ({headline, key}) => {
        return (
            <div className="link-item" key={key}>
                <a href={headline.url} target="_blank">
                    {headline.headline}
                </a>
            </div>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                CP24 {category.title}
            </CardTitle>
            <CardBody>
                {
                    error ? <FetchError /> :

                    headlines.data.map((headline, index) => 
                        <Headline headline={headline} key={index} />
                    )
                }
            </CardBody>
        </Card>
    );
}