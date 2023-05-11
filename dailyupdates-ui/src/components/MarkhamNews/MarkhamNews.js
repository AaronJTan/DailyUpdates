import Card from "../Card/Card";
import CardBody from "../Card/CardBody";
import CardTitle from "../Card/CardTitle";
import {getMarkhamNews} from "../../services/NewsService.js"
import FetchError from "../ErrorMessages/FetchError";

export default async function MarkhamNews() {
    let error = false;
    let headlines;

    try {
        headlines = await getMarkhamNews();
    } catch (err) {
        error = true;
    }

    const Headline = ({headline}) => {
        return (
            <div className="link-item">
                <a href={headline.link} target="_blank">
                    {headline.headline}
                </a>
            </div>
        );
    }

    return (
        <Card> 
            <CardTitle className="font-semibold">
                Markham News
            </CardTitle>
            <CardBody>
                {
                    error ? <FetchError /> :
                    headlines.map((headline) => 
                        <Headline headline={headline} />
                    )
                }
            </CardBody>
        </Card>
    );
}