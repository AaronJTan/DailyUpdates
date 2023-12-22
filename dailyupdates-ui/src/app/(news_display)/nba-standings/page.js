import NBASection from "@/components/sports/nba/NBASection";

export default function SportsPage() {

  return (
    <div className="container grid gap-2 grid-cols-2">
      <div className="col-span-full ">
        <NBASection />
      </div>
    </div>
  )
}
