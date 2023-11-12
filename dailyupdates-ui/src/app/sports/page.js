import NBAStanding from "@/components/NBAStanding";

export default function SportsPage() {

  return (
    <div className="container grid gap-2 grid-cols-2">
      <div className="col-span-full ">
        <NBAStanding />
      </div>
    </div>
  )
}
