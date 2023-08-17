import CP24News from "@/components/CP24/CP24News";
import { cp24Category } from "@/components/CP24/categories";
import MarkhamNews from "@/components/MarkhamNews";
import RedFlagDeals from "@/components/RedFlagDeals";
import TheHackerNews from "@/components/TheHackerNews";

export default function Home() {

  return (
    <div className="container grid gap-2 grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
      <div className="col-span-full ">
        <RedFlagDeals />
      </div>
      <div className="col-span-full lg:col-span-2 ">
        <MarkhamNews />
      </div>
      <div className="col-span-full lg:col-span-2 ">
        <CP24News category={cp24Category.LATESTNEWS} />
      </div>
      <div className="col-span-full lg:col-span-2 ">
        <TheHackerNews />
      </div>
      <div className="col-span-full lg:col-span-2 ">
        <CP24News category={cp24Category.WORLDNEWS} />
      </div>
    </div>
  )
}
